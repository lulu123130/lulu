<script setup>
/**
 * @description 地图位置
 * 格式: lng;lat;address
 */
import { ref, watchEffect } from 'vue'
defineOptions({
  inheritAttrs: false,
})
const { value, label, column } = defineProps(['value', 'label', 'column'])
let columnName = column.columnName
let text = ref('')
watchEffect(() => {
  if (!value) {
    text.value = ''
    return
  }

  if (columnName == 'fulladdress') {
    text.value = value
  } else {
    text.value = value.split(';')[2]
  }
})
</script>

<template>
  <div class="detail-text">
    <span class="label">{{ label }}</span>
    <span class="text">{{ text }}</span>
  </div>
</template>
