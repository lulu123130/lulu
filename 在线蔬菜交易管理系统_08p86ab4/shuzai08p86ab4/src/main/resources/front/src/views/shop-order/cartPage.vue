<script setup>
import '@/style/exam.scss'
import '@/components/TableItem/index'

import { computed, ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'

import CenterTitle from '@/views/center/CenterTitle.vue'
import { addAPI, deleteAPI, getDetailAPI, getListAPI } from '@/api/list'
import tableConfigs from '@/utils/tableConfigs'

let router = useRouter()
const tableName = 'cart'
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
  },
  {
    columnName: 'buynumber',
    comments: '数量',
    table_type: 'TableBuyNumber',
  },
  {
    columnName: 'total',
    comments: '总价',
    table_type: 'TableTotalPrice',
  },
]
// ----------------------------------
// ---------- 多选框 ---------------
// ----------------------------------
const selectedDatas = ref([])
const selectionChange = val => {
  selectedDatas.value = val
}
// 选中的总价
let totalPrice = computed(() => {
  let list = selectedDatas.value
  let total = list.reduce((preNum, item) => {
    return preNum + item.buynumber * item.price
  }, 0)
  return Number(total.toFixed(2))
})

const list = ref([])
const isLoading = ref(false)
fetchData()
async function fetchData() {
  isLoading.value = true
  try {
    let params = {
      limit: 1000,
      userid: Number(localStorage.getItem('userid')),
    }
    let res = await getListAPI(tableName, params)


    list.value = res.data.list || []
  } catch (error) {}
  isLoading.value = false
}

// 删除
function removes(rows) {
  if (!rows.length) {
    ElMessage.error('请选择要删除的数据')
    return
  }
  ElMessageBox.confirm('确认删除?', '提示', {
    confirmButtonText: '删除',
    cancelButtonText: '取消',
    type: 'warning',
  })
    .then(async () => {
      let ids = rows.map(item => item.id)
      await deleteAPI(tableName, ids)
      selectedDatas.value = []
      fetchData()
      ElMessage.success('删除成功')
    })
    .catch(() => {})
}

// 跳购买确认界面
const btnLoading = ref(false)
async function toOrderConfirm() {
  btnLoading.value = true
  try {
    if (!selectedDatas.value.length) {
      throw new Error('请先选择商品')
    }
    // 判断库存和单限
    for (let index = 0; index < selectedDatas.value.length; index++) {
      const item = selectedDatas.value[index]
      let { tablename: tableName, buynumber, goodid, goodname } = item
      let limit = tableConfigs[tableName].table.limit
      if (limit != '是') {
        continue
      }
      let { data } = await getDetailAPI(tableName, goodid)
      let { onelimittimes, alllimittimes } = data
      // 判断单限
      if (onelimittimes >= 0 && buynumber > onelimittimes) {
        throw new Error(goodname + ',每单最多限制:' + onelimittimes)
      }
      // 判断库存
      if (buynumber > alllimittimes) {
        throw new Error(goodname + ',库存不足')
      }
    }

    // 生成未支付订单
    let orderDatas = selectedDatas.value.map(item => {
      let orderData = {
        ...item,
        total: Number(Number(item.price * item.buynumber).toFixed(2)),
        type: 1,
        role: localStorage.getItem('sessionTable'),
      }
      delete orderData.id
      delete orderData.addtime

      return orderData
    })

    // 移除购物车
    deleteAPI(
      tableName,
      selectedDatas.value.map(item => item.id)
    )

    router.push({
      path: '/index/orderConfirm',
      state: {
        orderDatas,
        type: 1,
      },
    })
  } catch (error) {
    ElMessage.error(error.message)
  }
  btnLoading.value = false  
}

onMounted(() => {
  setTimeout(() => {
    requestIdleCallback(() => {
      import('@/views/shop-order/orderConfirmPage.vue')
    })
  }, 1000)
})
</script>
<template>
  <div class="exam-page">
    <!-- 标题 -->
    <div class="examheader-wrapper">
      <CenterTitle title="购物车" />
    </div>

    <div class="examtable-wrapper" v-loading="isLoading">
      <div class="wrapper-header">
        <el-button
          type="danger"
          @click="removes(selectedDatas)"
          size="small"
          :disabled="!selectedDatas.length"
        >
          删除
        </el-button>
      </div>
      <el-table :data="list" @selection-change="selectionChange">
        <el-table-column type="selection" width="55" />

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

        <el-table-column label="操作">
          <template #default="{ row }">
            <div class="table-button-wrapper">
              <el-button @click="removes([row])" size="small" type="danger">删除</el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="pagination-wrapper">
      <div class="cart-totalprice">
        <span>总价:</span>
        <span class="price">{{ totalPrice }}</span>
        <div class="btn-wrapper">
          <el-button type="primary" :loading="btnLoading" plain @click="toOrderConfirm">
            下单
          </el-button>
        </div>
      </div>
    </div>
  </div>
</template>
