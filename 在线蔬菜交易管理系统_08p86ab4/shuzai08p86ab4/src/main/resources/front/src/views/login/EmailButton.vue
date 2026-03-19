
<script setup>
import { inject } from 'vue'
const { hasEmail, emailEvent, emailType } = inject('login')
</script>
<template>
  <el-link v-show="hasEmail" class="email-btn" @click="emailEvent">{{ emailType }}</el-link>
</template>

