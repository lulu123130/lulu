<script setup>
import { ref, reactive } from 'vue'
import { getDetailAPI, updateAPI } from '@/api/list'
import tableConfigs from '@/utils/tableConfigs'
defineOptions({
  inheritAttrs: false,
})
const visible = defineModel()
const { data, tableName } = defineProps(['data', 'tableName'])
const { row, updateMerchantMoney } = data
const emits = defineEmits(['fetchData'])

// 表单实例
const ruleFormRef = ref()
// 表单验证规则
let rules = reactive({
  sfsh: [
    {
      required: true,
      message: '请输入审核结果',
    },
  ],
  shhf: [
    {
      required: true,
      message: '请输入回复内容',
    },
  ],
})
// 表单数据
let ruleForm = reactive({
  sfsh: '待审核',
  shhf: '',
})
const isLoading = ref(false)
const options = [
  {
    value: '是',
    label: '通过',
  },
  {
    value: '否',
    label: '不通过',
  },
  {
    value: '待审核',
    label: '待审核',
  },
]

// 确认事件
const submitEvent = async () => {
  // 表单校检逻辑
  let valid = await ruleFormRef.value.validate((valid, fields) => {
    if (!valid) {
      // 验证不通过，提示第一个错误
      let firstErrorField = Object.entries(fields)
      let firstErrorMessage = firstErrorField[0][1][0].message || '表单校验失败，请检查输入'
      ElMessage.error(firstErrorMessage)
    }
  })
  if (!valid) return

  isLoading.value = true
  try {
    row.sfsh = ruleForm.sfsh
    row.shhf = ruleForm.shhf

    if (ruleForm.sfsh == '是') {
      row.status = '已退款'

      // 退款逻辑
      let { userid, role, tablename: tableName, goodid, buynumber } = row
      let { data: userForm } = await getDetailAPI(role, userid)

      // 增加余额
      userForm.money = Number((userForm.money + row.total).toFixed(2))
      updateAPI(role, userForm)

      // 扣除商户余额
      updateMerchantMoney(row, -row.total)
      // 增加库存
      let table = tableConfigs[tableName].table
      if (table.limit == '是') {
        getDetailAPI(tableName, goodid).then(({ data }) => {
          data.alllimittimes += buynumber
          updateAPI(tableName, data)
        })
      }
    }    
    
    await updateAPI('orders', row)
    emits('fetchData')
    visible.value = false
  } catch (error) {
    ElMessage.error(error.msg || error.message || '审核失败')
  }

  isLoading.value = false
}
</script>

<template>
  <el-form
    class="editform"
    :model="ruleForm"
    :rules="rules"
    ref="ruleFormRef"
    @submit.prevent="submitEvent"
  >
    <el-form-item prop="sfsh" label="审核状态">
      <el-select v-model="ruleForm.sfsh" placeholder="请选择审核状态">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        />
      </el-select>
    </el-form-item>

    <el-form-item prop="shhf" label="回复内容">
      <el-input v-model="ruleForm.shhf" placeholder="请输入回复内容" type="textarea" :rows="4" />
    </el-form-item>

    <el-form-item prop="returnreason" label="退货原因">
      <div>{{ row.returnreason }}</div>
    </el-form-item>

    <!-- 按钮 -->
    <div class="btn-wrapper">
      <!-- 注册 -->
      <div class="submit-box">
        <el-button class="submit-btn" :loading="isLoading" native-type="submit">提交</el-button>
      </div>

      <!-- 登录 -->
      <div class="cancel-box">
        <el-button class="cancel-btn" @click="visible = false">取消</el-button>
      </div>
    </div>
  </el-form>
</template>
