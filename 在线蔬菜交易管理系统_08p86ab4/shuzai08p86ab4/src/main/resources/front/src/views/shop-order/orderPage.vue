<script setup>
import '@/style/exam.scss'
import '@/components/TableItem/index'

import { ref, watchEffect, shallowRef, computed } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import CenterTitle from '@/views/center/CenterTitle.vue'

import LogisticsInfo from './LogisticsInfo.vue'
import OrderReview from './OrderReview.vue'
import Pay from '@/components/Pay.vue'
import ListEdit from '@/views/list/ListEdit.vue'

import { getListAPI, getPageAPI, updateAPI, getDetailAPI } from '@/api/list'
import { getOrdersListAPI } from '@/api/front'
import tableConfigs from '@/utils/tableConfigs'
import { toDetailPage } from '@/utils/navigate'
import { isAuth } from '@/utils/auth'
import { getNanoId } from '@/utils'

const router = useRouter()
const route = useRoute()
let { status } = route.query
const tableName = 'orders'
const userid = Number(localStorage.getItem('userid'))
const username = localStorage.getItem('username')
const merchantName = 'maijiazhanghao'
const merchantRole = 'shangjia'

const actName = ref(status ? status : '已支付')

let initColumns = [
  {
    columnName: 'orderid',
    comments: '订单编号',
  },
  {
    columnName: 'goodname',
    comments: '商品',
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
  // 地址
  {
    columnName: 'address',
    comments: '地址',
  },
  {
    columnName: 'tel',
    comments: '电话',
  },
  {
    columnName: 'consignee',
    comments: '收货人',
  },
// 退货审核
  {
    columnName: 'sfsh',
    comments: '退货审核',
    table_type: 'TableTag',
    options: [
      {
        value: '是',
        label: '已通过',
        type: 'success',
      },
      {
        value: '待审核',
        label: '待审核',
        type: 'info',
      },
      {
        value: '否',
        label: '未通过',
        type: 'danger',
      },
    ],
  },
  {
    columnName: 'shhf',
    comments: '退货审核回复',
  },
]

const columns = computed(() => {
  let _columns = initColumns.slice(0)
  let actNameList_sfsh = ['未支付', '已支付', '待审核']
  if (actNameList_sfsh.includes(actName.value)) {
    let columnName_filter = ['sfsh', 'shhf']
    _columns = _columns.filter(item => !columnName_filter.includes(item.columnName))
  }
  return _columns
})

const list = ref([])
const isLoading = ref(false)
async function fetchData() {
  list.value = []
  isLoading.value = true
  try {
    let params = {
      // 分页
      limit: 1000,
      page: 1,
      status: actName.value,
    }
    let res = await getOrdersListAPI(params)

    list.value = res.data.list || []
  } catch (error) {}
  isLoading.value = false
}
watchEffect(() => {
  fetchData()
})

// 前往支付界面
function toOrderConfirm(row) {
  ElMessage.info('此功能还未添加')  
}
// 取消
async function cancle(row) {
  row.status = '已取消'
  await updateAPI(tableName, row)
  fetchData()
}
// 退款
function refund(row) {
  ElMessageBox.confirm('确认退款?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  })
    .then(async () => {
      let sessionTable = localStorage.getItem('sessionTable')
      let userForm = JSON.parse(localStorage.getItem('userForm'))

      // 增加余额
      userForm.money = Number((userForm.money + row.total).toFixed(2))
      updateAPI(sessionTable, userForm)
      localStorage.setItem('userForm', JSON.stringify(userForm))

      let { tablename: tableName, goodid, buynumber } = row
      // 增加库存
      let table = tableConfigs[tableName].table
      if (table.limit == '是') {
        getDetailAPI(tableName, goodid).then(({ data }) => {
          data.alllimittimes += buynumber
          updateAPI(tableName, data)
        })
      }

      row.status = '已退款'
      await updateAPI('orders', row)

      fetchData()
      ElMessage.success('退款成功')
    })
    .catch(() => {})
}
// 查看物流
function showLogistics(row) {
  dialogClass.value = ''
  dialogComponent.value = LogisticsInfo
  dialogTitle.value = '物流'
  dialogData = {
    row,
    isEdit: false,
  }
  dialogVisible.value = true
}
// 确认收货
function comfirmReceipt(row, msg) {
  ElMessageBox.confirm(msg, '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  })
    .then(async () => {
      row.status = '已完成'      
      await updateAPI(tableName, row)
      // 更新销量
      getDetailAPI(row.tablename, row.goodid).then(res => {
        let good = res.data
        if (good.sales || good.sales == 0) {
          good.sales += row.buynumber
          updateAPI(row.tablename, good)
        }
      })
      // 更新商户余额
      updateMerchantMoney(row, row.total)

      ElMessage.success('操作成功')
      fetchData()
    })
    .catch(() => {})
}
// 更新商户余额
async function updateMerchantMoney(row, amount) {
  let res = await getListAPI(merchantRole, {
    limit: 1,
    [merchantName]: row[merchantName]
  })
  let merchantInfo = res.data.list[0]
  if (!merchantInfo) {
    return
  }

  // 更新 余额
  merchantInfo.money += amount
  merchantInfo.money = Number(merchantInfo.money.toFixed(2))

  await updateAPI(merchantRole, merchantInfo)
}
// 退货退款
function returnGood(row) {
  ElMessageBox.prompt('请填写退货理由。审核通过后退款金额将返回账户中', '退货申请', {
    confirmButtonText: '提交',
    cancelButtonText: '取消',
    inputType: 'textarea',
  })
    .then(async ({ value }) => {
      row.returnreason = value
      row.sfsh = '待审核'
      await updateAPI(tableName, row)
      ElMessage.success('已提交申请。')
    })
    .catch(() => {})
}


function showQrCode(row) {
  dialogClass.value = ''
  dialogComponent.value = Pay
  dialogTitle.value = '二维码'
  dialogData = { hasPay: '二维码', hideActionButton: true }
  dialogVisible.value = true
}

// ----------------------------------
// ---------- 弹框 ---------------
// ----------------------------------
const dialogVisible = ref(false)
const dialogTitle = ref('弹框标题')
const dialogComponent = shallowRef(null)
const dialogClass = ref('')
let dialogData = {}


// ----------------------------------
// ----------- 余额支付 --------------
// ----------------------------------
async function moneyPay(row) {
  let loading = ElMessage.info({
    duration: 0,
    message: '支付中...',
  })

  try {
    row.status = '已支付'

    // 判断余额
    let sessionTable = localStorage.getItem('sessionTable')
    let userForm = JSON.parse(localStorage.getItem('userForm'))
    if (userForm.money < row.total) {
      throw new Error('余额不足，请前往个人中心充值')
    }

    // 单限
    let isError_limit = await limitEvent(row)
    if (isError_limit) {
      throw new Error('库存不足')
    }



    // 更新订单信息
    await updateAPI('orders', row)

    // 更新用户数据
    userForm.money = Number((userForm.money - row.total).toFixed(2))
    updateAPI(sessionTable, userForm)
    localStorage.setItem('userForm', JSON.stringify(userForm))

    ElMessage.success('操作成功')
  } catch (error) {
    ElMessage.error(error.message || error.msg || '')
  }
  fetchData()
  loading.close()
}




// 单限功能
async function limitEvent(orderData) {
  let { tablename: tableName, goodid, buynumber } = orderData
  let table = tableConfigs[tableName].table
  if (table.limit != '是') {
    return false
  }

  let { data } = await getDetailAPI(tableName, goodid)

  data.alllimittimes -= buynumber

  // 库存不足
  if (data.alllimittimes < 0) {
    return true
  }
  // 库存为0，下架商品
  if ((data.alllimittimes == 0)) {
    data.onshelves = 0
  }
  await updateAPI(tableName, data)
  return false
}



</script>

<template>
  <div class="exam-page">
    <!-- 标题 -->
    <div class="examheader-wrapper">
      <CenterTitle title="我的订单" />
    </div>

    <div class="examtable-wrapper">
      <el-tabs v-model="actName" class="detail-tabs" type="card">
        <el-tab-pane label="未支付" name="未支付"></el-tab-pane>
        <el-tab-pane label="已支付" name="已支付"></el-tab-pane>
        <el-tab-pane  label="已发货" name="已发货"></el-tab-pane>
        <el-tab-pane label="已完成" name="已完成"></el-tab-pane>
        <el-tab-pane label="已退款" name="已退款"></el-tab-pane>
        <el-tab-pane label="已取消" name="已取消"></el-tab-pane>
      </el-tabs>

      <el-table :data="list" v-loading="isLoading" size="small" >
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
        <el-table-column label="操作" min-width="160">
          <template #default="{ row }">
            <div class="table-button-wrapper">
                <el-button
                  v-if="row.logistics"
                  @click="showLogistics(row)"
                  type="success"
                  size="small"
                  plain
                >
                  物流
                </el-button>
                <template v-if="actName == '未支付'">
                  <el-button v-if="row.type != 2" @click="moneyPay(row)" size="small" plain>余额支付</el-button>
                  <el-button
                    @click="cancle(row)"
                    size="small"
                    type="info"
                    plain
                  >
                    取消
                  </el-button>
                </template>
                <template v-else-if="actName == '已支付'">
                  <el-button
                    v-if="row.type != 2"
                    @click="refund(row)"
                    type="danger"
                    size="small"
                    plain
                  >
                    退款
                  </el-button>
                  <el-button
                    v-if="isAuth('orders/' + actName, '二维码')"
                    @click="showQrCode(row)"
                    size="small"
                    plain
                  >
                    二维码
                  </el-button>
                </template>

                <template v-else-if="actName == '已发货'">               
                  <el-button
                    @click="comfirmReceipt(row, '确认收货?')"
                    type="success"
                    size="small"
                    plain
                  >
                    确认收货
                  </el-button>
                </template>

                <template v-else-if="actName == '已完成'">
                  <el-button
                    v-if="row.type != 2"
                    @click="returnGood(row)"
                    :disabled="!!row.sfsh"
                    type="danger"
                    size="small"
                    plain
                  >
                    退货
                  </el-button>
                  <el-button
                    @click="toDetailPage(row.tablename, row.goodid)"
                    type="success"
                    size="small"
                    plain
                  >
                    评价
                  </el-button>
                </template>
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
        :tableName="tableName"
        :data="dialogData"
        :class="dialogClass"
        @fetchData="fetchData"
      />
    </el-dialog>  
  </div>
</template>
