<script setup>
import { ref } from 'vue'
import { updateAPI } from '@/api/list'
import YyQuill from '@/components/FormItem/components/YyQuill.vue'
defineOptions({
  inheritAttrs: false,
})

const visible = defineModel()
const emits = defineEmits(['fetchData'])
const { data, tableName } = defineProps(['data', 'tableName'])
let { row, isEdit } = data

const columns = [
  {
    columnName: 'logistics',
    comments: '物流信息',
  },
]
const isLoading = ref(false)
const ruleForm = ref({
  logistics: row.logistics,
})
async function okEvent() {
  isLoading.value = true
  try {
    row.logistics = ruleForm.value.logistics
    await updateAPI(tableName, row)
    ElMessage.success('更新成功')
    visible.value = false
  } catch (error) {}
  isLoading.value = false
}
</script>

<template>
  <!-- 更新 -->
  <el-form v-if="isEdit" class="editform" :model="ruleForm" @submit.prevent>
    <el-form-item>
      <YyQuill
        :columns="columns"
        :column="columns[0]"
        :ruleForm="ruleForm"
        :tableName="tableName"
        :disabled="false"
      />
    </el-form-item>

    <div class="btn-wrapper">
      <div class="submit-box">
        <el-button :loading="isLoading" class="submit-btn" @click="okEvent">提交</el-button>
      </div>

      <div class="cancel-box">
        <el-button class="cancel-btn" @click="visible = false">取消</el-button>
      </div>
    </div>
  </el-form>

  <!-- 查看  -->
  <div v-else class="ql-snow ql-editor" v-html="row.logistics"></div>
</template>
