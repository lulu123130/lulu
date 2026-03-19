<script setup>
import { computed } from 'vue'

defineOptions({
  inheritAttrs: false,
})
const { row } = defineProps(['row'])
let totalPrice = computed(() => {
  return Number((row.buynumber * row.price).toFixed(2))
})
</script>

<template>
  <span>{{ totalPrice }}</span>
</template>

<style></style>
