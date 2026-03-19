<script setup>
/**
 * @description 支付组件
 */
import { ref } from 'vue'
import { updateAPI } from '@/api/list'
import weixin from '@/assets/img/pay/weixin.png'
import zhifubao from '@/assets/img/pay/zhifubao.png'
import jianshe from '@/assets/img/pay/jianshe.png'
import nongye from '@/assets/img/pay/nongye.png'
import zhongguo from '@/assets/img/pay/zhongguo.png'
import jiaotong from '@/assets/img/pay/jiaotong.png'
import QRcode from '@/assets/img/QRcode.png'
import { csuEvent } from '@/utils/feature'
const list = [
  {
    label: '微信支付',
    src: weixin,
  },
  {
    label: '支付宝支付',
    src: zhifubao,
  },
  {
    label: '农业银行',
    src: nongye,
  },
  {
    label: '建设银行',
    src: jianshe,
  },
  {
    label: '中国银行',
    src: zhongguo,
  },
  {
    label: '交通银行',
    src: jiaotong,
  },
]


defineOptions({
  inheritAttrs: false,
})


const visible = defineModel()
const emits = defineEmits(['fetchData'])
const { data, tableName } = defineProps(['data', 'tableName'])
let { rows, hasPay, hideActionButton  } = data

const type = ref('微信支付')
const isLoading = ref(false)

const okEvent = async () => {
  isLoading.value = true
  try {
    for (let index = 0; index < rows.length; index++) {
      const row = rows[index]

      // 跨表修改
      let csuRes = await csuEvent('支付', tableName, row)
      if (csuRes.isError) {
        throw new Error(csuRes.errorMsg)
      }

      row.ispay = '已支付'
      await updateAPI(tableName, row)
     
    }

    ElMessage.success('支付成功')
    emits('fetchData')
    visible.value = false
  } catch (error) {
    let msg = error.msg || error.message || '支付失败'
    ElMessage.error(msg)
  }
  isLoading.value = false
}
</script>

<template>
  <el-form class="editform pay-wrapper" @submit.prevent>
    <div v-if="hasPay == '二维码'" class="qrcode-item"><img :src="QRcode" draggable="false" /></div>

    <template v-else>
      <el-form-item v-for="item in list">
        <div class="pay-type-item" @click="type = item.label">
          <el-radio v-model="type" :label="item.label"></el-radio>
          <img :src="item.src" alt draggable="false" />
        </div>
      </el-form-item>
    </template>   

    <div class="btn-wrapper" v-if="!hideActionButton">
      <div class="submit-box">
        <el-button :loading="isLoading" class="submit-btn" @click="okEvent">提交</el-button>
      </div>

      <div class="cancel-box">
        <el-button class="cancel-btn" @click="visible = false">取消</el-button>
      </div>
    </div>
  </el-form>
</template>
<style lang="scss">
.pay-type-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  border: 1px solid #eeeeee;
  padding: 20px;
  background: #fff;
  cursor: pointer;
}

.qrcode-item {
  text-align: center;
  width: 100%;
}
</style>
