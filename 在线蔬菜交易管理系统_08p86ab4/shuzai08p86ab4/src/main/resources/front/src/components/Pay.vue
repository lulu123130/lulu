<script setup>
/**
 * @description 支付组件/二维码组件
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

let { data } = defineProps(['data'])
let hasPay = data?.hasPay
let hideActionButton = data?.hideActionButton
const emits = defineEmits(['payOk'])

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

const type = ref('微信支付')
const visible = defineModel()

</script>

<template>
  <el-form class="editform pay-wrapper" @submit.prevent>
    <slot></slot>

    <div v-if="hasPay == '二维码'" class="qrcode-item"><img :src="QRcode" alt /></div>

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
        <el-button :loading="isLoading" class="submit-btn" @click="emits('payOk')">提交</el-button>
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
