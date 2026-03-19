<script setup>
import '@/style/exam.scss'
import '@/components/TableItem/index'

import { computed, ref, shallowRef, provide } from 'vue'
import { useRouter, useRoute } from 'vue-router'

import CenterTitle from '@/views/center/CenterTitle.vue'
import ListEdit from '../list/ListEdit.vue'

import { addAPI, deleteAPI, getDetailAPI, getListAPI, getPageAPI, updateAPI } from '@/api/list'
import tableConfigs from '@/utils/tableConfigs'
import { toDetailPage, toListPage } from '@/utils/navigate'
import { getNanoId } from '@/utils'

let router = useRouter()
let { orderDatas = [], type = 1 } = history.state

const tableName = 'orders'
let title = '订单支付'
let columns = [
  {
    columnName: 'goodname',
    comments: '名称',
  },
  {
    columnName: 'picture',
    comments: '图片',
    table_type: 'TableImage',
  },
  {
    columnName: 'price',
    comments: '价格',
    table_type: 'TablePrice',
  },
  {
    columnName: 'buynumber',
    comments: '数量',
  },
  {
    columnName: 'total',
    comments: '总价',
    table_type: 'TablePrice',
  },
]

const remark = ref('') // 备注
const orders = ref(orderDatas)
const orderLoading = ref(false)
const isLoading = ref(false) // 按钮公共loading


// ----------------------------------
// ---------- 购物车地址 -------------
// ----------------------------------

const addressTableName = 'address'
const addressColumns = tableConfigs[addressTableName].columns
const addressTableRef = ref()
const addressList = ref([])
const addressLoading = ref(false)
getAddressList()
async function getAddressList() {
  addressLoading.value = true
  try {
    let apiParams = {
      userid: Number(localStorage.getItem('userid')),
      limit: 999,
    }
    let res = await getListAPI(addressTableName, apiParams)
    addressList.value = res.data.list

    // 默认选中地址
    let defaultAddress = addressList.value.find(item => item.isdefault === '是')
    if (defaultAddress) {
      addressTableRef.value.setCurrentRow(defaultAddress)
    }
  } catch (error) {}
  addressLoading.value = false
}
const address = ref()
function handleCurrentChange(val) {
  address.value = val
}

// ----------------------------------
// ----------- 弹框 -----------------
// ----------------------------------
const dialogVisible = ref(false)
const dialogTitle = ref('弹框标题')
const dialogComponent = shallowRef(null)
const dialogClass = ref('')
let dialogData = {}
function addAddress() {
  dialogTitle.value = '新增地址'
  dialogComponent.value = ListEdit
  dialogClass.value = ''
  dialogData = {
    type: 'add', // add: 新增 update: 编辑 cross: 跨表
    id: '',
    tableName: addressTableName,
  }
  dialogVisible.value = true
}
function editAddress(row) {
  dialogTitle.value = '编辑地址'
  dialogClass.value = ''
  dialogComponent.value = ListEdit
  dialogData = {
    type: 'update',
    id: row.id,
    tableName: addressTableName,
    row,
  }
  dialogVisible.value = true
}
function removeAddress(row) {
  ElMessageBox.confirm('确认删除?', '提示', {
    confirmButtonText: '删除',
    cancelButtonText: '取消',
    type: 'warning',
  })
    .then(async () => {
      let ids = [row.id]
      await deleteAPI(addressTableName, ids)
      ElMessage.success('删除成功')
      getAddressList()
    })
    .catch(() => {})
}


// ----------------------------------
// ------------- 总价 ---------------
// ----------------------------------
let totalPrice = ref(0)
getTotalPrice()
function getTotalPrice() {
  // 更新总价
  let total = orders.value.reduce((preNum, item) => {
    return preNum + item.buynumber * item.price
  }, 0)

  // 更新单个商品总价
  orders.value.forEach(item => (item.total = Number((item.buynumber * item.price).toFixed(2))))


  totalPrice.value = Number(total.toFixed(2))
}

// ----------------------------------
// ----------- 余额支付 --------------
// ----------------------------------
async function moneyPay() {
  isLoading.value = true
  try {

    // 收货地址
    if (!address.value ) {
      throw new Error('请先选择收货地址')
    }

    // 更新订单数据  
    let orders_unpay = orders.value.map(order => {
      // 新增订单
      let data = {
        ...order,
        orderid: getNanoId(),
        discounttotal: order.total,
        remark: remark.value,
        sfsh: '',
        status: '未支付',

        // 收货地址
        address: address.value.address,
        consignee: address.value.name,
        tel: address.value.phone,

      }
      return data
    })

    // 判断余额是否充足
    let sessionTable = localStorage.getItem('sessionTable')
    let userForm = JSON.parse(localStorage.getItem('userForm'))     
    if (userForm.money < totalPrice.value) {

      ElMessageBox.confirm('是否前往个人中心充值?', '余额不足', {
        confirmButtonText: '充值',
        cancelButtonText: '取消',
        type: 'warning',
      })
        .then(async () => {
          router.push('/index/center')
        })
        .catch(() => {})
        
      // 生成未支付的订单
      let unpayPromiseList = orders_unpay.map(async data => {
        await addAPI(tableName, data)
        return true
      })
      await Promise.all(unpayPromiseList)
      router.push({ path: '/index/shop-order/order', query: { status: '未支付' } }) 
      throw new Error('购买失败，余额不足。请先在个人中心充值。') 
    }

    // 下单,并修改为 已支付
    let promiseList = orders_unpay.map(async data => {
      try {
        data.status = '已支付'
        let res = await addAPI(tableName, data)
        data.id = res.data

        // 减少余额
        userForm.money = Number((userForm.money - data.total).toFixed(2))


        // 单限
        limitEvent(data)


        return true
      } catch (error) {
        throw new Error('支付失败')
      }
    })

    await Promise.all(promiseList)
    // 更新用户数据
    updateAPI(sessionTable, userForm)
    localStorage.setItem('userForm', JSON.stringify(userForm))

    ElMessage.success('操作成功')
    router.push('/index/shop-order/order')
  } catch (error) {
    ElMessage.error(error.message)
  }
  isLoading.value = false
}




// 单限功能
async function limitEvent(orderData) {
  let { tablename: tableName, goodid, buynumber } = orderData
  let table = tableConfigs[tableName].table
  if (table.limit != '是') {
    return
  }

  let { data } = await getDetailAPI(tableName, goodid)
  data.alllimittimes -= buynumber
  // 库存不足，下架商品
  if (data.alllimittimes <= 0) {
    data.onshelves = 0
  }
  updateAPI(tableName, data)
}



provide('detail', {
  detailEvent: toDetailPage,
  moreEvent: toListPage,
})
</script>
<template>
  <div class="exam-page">
    <!-- 标题 -->
    <div class="examheader-wrapper">
      <CenterTitle :title="title" />
    </div>

    <!-- 收货地址 -->
    <div class="examtable-wrapper" >
      <div class="wrapper-header">
        收货地址
        <el-button size="small" @click="addAddress">添加</el-button>
      </div>
      <el-table
        :data="addressList"
        v-loading="addressLoading"
        ref="addressTableRef"
        highlight-current-row
        @current-change="handleCurrentChange"
      >
        <el-table-column
          v-for="(column, index) in addressColumns"
          :prop="column.columnName"
          :label="column.comments"
          :key="column.columnName"
        >
          <template #default="scope">
            <component
              :is="column.table_type"
              :row="scope.row"
              :column="column"
              :value="scope.row[column.columnName]"
            />
          </template>
        </el-table-column>

        <el-table-column label="操作">
          <template #default="{ row }">
            <div class="table-button-wrapper">
              <el-button @click="editAddress(row)" size="small">编辑</el-button>
              <el-button @click="removeAddress(row)" size="small">删除</el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-dialog
      class="yy-dialog"
      v-model="dialogVisible"
      :title="dialogTitle"
      destroy-on-close
      :close-on-click-modal="false"
    >
      <component
        v-model="dialogVisible"
        :is="dialogComponent"
        :tableName="addressTableName"
        :data="dialogData"
        :class="dialogClass"
        @fetchData="getAddressList"
      />
    </el-dialog>

    <!-- 列表 -->
    <div class="examtable-wrapper">
      <div class="wrapper-header">清单列表</div>
      <el-table :data="orders" v-loading="orderLoading">
        <el-table-column
          v-for="(column, index) in columns"
          :prop="column.columnName"
          :label="column.comments"
          :key="column.columnName"
        >
          <template #default="scope">
            <component
              :is="column.table_type"
              :row="scope.row"
              :column="column"
              :value="scope.row[column.columnName]"
            />
          </template>
        </el-table-column>
      </el-table>
    </div>


    <!-- 备注 -->
    <div class="examtable-wrapper">
      <div class="wrapper-header">
        <span>备注</span>
      </div>
      <div>
        <el-input type="textarea" v-model="remark"/>
      </div>
    </div>

    <!-- 购买 -->
    <div class="pagination-wrapper">
      <div class="cart-totalprice">
        <span>总价:</span>
        <span class="price">{{ totalPrice }}</span>
        <div v-loading="isLoading">
          <template v-if="type != 2">      
            <el-button  type="primary" plain @click="moneyPay">余额支付</el-button>
          </template>
        </div>
      </div>
    </div>


  </div>
</template>
