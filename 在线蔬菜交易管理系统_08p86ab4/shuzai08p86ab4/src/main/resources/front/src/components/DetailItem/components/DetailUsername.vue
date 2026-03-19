<script setup>
import { computed, inject } from 'vue'
import { useRouter } from 'vue-router'
import { commonTableAPI } from '@/api/common'

const router = useRouter()
defineOptions({
  inheritAttrs: false,
})
const { value, label, data, column } = defineProps(['value', 'label', 'data', 'column'])
let { merchantName, centerType } = inject('detail')
let isLink = computed(() => {
  return merchantName && merchantName == column.columnName
})
async function clickEvent() {
  if (!isLink.value) {
    return
  }
  let res = await commonTableAPI({
    url: column.authTable + '/query',
    method: 'get',
    params: {
      [merchantName]: data[merchantName],
    },
  })
  let id = res.data.id

  router.push({
    path: '/index/' + column.authTable + 'Detail',
    query: {
      id,
      centerType,
    },
  })
}
</script>

<template>
  <div class="detail-text">
    <span class="label">{{ label }}</span>
    <span class="text" :class="isLink ? 'url' : ''" @click="clickEvent">
      {{ data.isanon == 1 ? '匿名' : value }}
    </span>
  </div>
</template>
