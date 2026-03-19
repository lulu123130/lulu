<script setup>
/**
 * @description 购物车的数量
 */

defineOptions({
  inheritAttrs: false,
})
const { row } = defineProps(['row'])

</script>

<template>
  <el-input-number v-model="row.buynumber" :min="1" :step="1" :precision="0" />
</template>

