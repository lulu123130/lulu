<script setup>
/**
 * @description 说明
 * 1.参数:
 * 1.1 ruleForm 表单对象
 * 1.1.1 ruleForm.tableName 当前选中的角色
 *
 * 1.2 roleOptions 角色列表
 * item.value 值
 * item.label 文本
 *
 * 1.3 isShow 大于2个角色，才显示
 */
import { inject } from 'vue'

const { ruleForm, roleOptions } = inject('login')
// 大于2个角色，才显示
let isShow = roleOptions.length > 1
</script>
<template>
  <el-form-item label="角色" class="role" v-show="isShow">
    <el-radio-group v-model="ruleForm.tableName">
      <el-radio v-for="item in roleOptions" :key="item.value" :value="item.value">
        {{ item.label }}
      </el-radio>
    </el-radio-group>
  </el-form-item>
</template>
<style>
.role {
  .el-radio__input.is-checked .el-radio__inner {
    background: var(--btn-bg-color-);
    border-color: var(--btn-bg-color-);
  }
  .el-radio__input.is-checked + .el-radio__label {
    color: var(--btn-bg-color-);
  }
}
</style>

