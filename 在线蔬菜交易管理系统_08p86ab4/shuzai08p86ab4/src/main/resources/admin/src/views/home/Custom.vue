<template>
  <el-calendar v-model="value" />
</template>

<script setup>
import { ref } from 'vue'

const value = ref(new Date())
</script>

<style>
.el-calendar{
  width: calc(50% - 10px);
  background: #fff;
  border-radius: 10px;
  padding: 10px;
}
.el-calendar__title{
  font-size:20px;
}
</style>