
<script setup>
import { inject } from 'vue'
const { registerOptions, registerEvent } = inject('login')
</script>
<template>
  <div class="register-box">
    <el-button
      v-for="item in registerOptions"
      :key="item.tableName"
      class="register-btn"
      @click="registerEvent(item)"
      type="success"
      plain
      color="#10c17c"
    >
      {{ item.roleName }} 注册
    </el-button>
  </div>
</template>

