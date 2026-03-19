<script setup>
import { computed } from 'vue'

defineOptions({
  inheritAttrs: false,
})
const { value, row } = defineProps(['value', 'row'])
let text = computed(() => {
  let { type } = row
  if (type == 2) {
    return value + '积分'
  }
  return value
})
</script>

<template>
  <span>{{ text }}</span>
</template>
