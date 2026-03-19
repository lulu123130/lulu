<script setup>
/**
 * @description 个人中心
 * ps: 几乎同后台,多了个featchData修改后更新个人信息
 */
// 注册表单组件
import '@/components/FormItem/index.js'
import { reactive, ref, inject } from 'vue'

import Pay from '@/components/Pay.vue'

import { getColums, getInitRules, getDisable } from '@/utils/form'
import { addAPI, updateAPI } from '@/api/list'
import { getSessionAPI } from '@/api/login'
import { useUserInfo } from '@/store'
import { getAvatar } from '@/utils'
import { clearFilePath } from '@/utils/getFilePath'
import { roleList } from '@/utils/role'

defineOptions({
  inheritAttrs: false,
})
let { infoEvent } = inject('center')

// 当前表名
let tableName = localStorage.getItem('sessionTable')

const userInfoStore = useUserInfo()

const isLoading = ref(false)
// 表单实例
const ruleFormRef = ref()
// 列字段
let columns = reactive(getColums(tableName, 'center'))
// 表单数据
let ruleForm = ref({})
// 表单验证规则
let rules = reactive(getInitRules(columns))
// 禁止操作字段
let disabledColumnNames = getDisable(tableName, columns, 'center')
/**
 * @description 提交事件
 */
const okEvent = async () => {
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
    await updateAPI(tableName, ruleForm.value)

    // 获取图像路径
    let [avatar, username] = getAvatar(ruleForm.value, tableName)
    userInfoStore.setUserInfo({
      avatar,
      username,
      isLogin: true,
    })

    // 缓存用户全部信息
    localStorage.setItem('userForm', JSON.stringify(ruleForm.value))
    // 用户头像
    localStorage.setItem('useravatar', clearFilePath(avatar))
    // 用户名
    localStorage.setItem('username', username)

    ElMessage.success('操作成功')

    // 更新个人信息
    infoEvent()
  } catch (error) {
    let errorMsg = error.msg || error.message || ''
    ElMessage.error(`操作失败： ${errorMsg}`)
  }

  isLoading.value = false
}

// 拉取详情数据
getInfo()
async function getInfo() {
  let res = await getSessionAPI(tableName)
  ruleForm.value = res.data
}

// ----------------------------------
// ----------- 余额充值 --------------
// ----------------------------------
const moneyVisible = ref(false)
const money = ref(100)
function openMoneyDialog() {
  moneyVisible.value = true
}
async function moneyPayOkEvent() {
  let userForm = JSON.parse(localStorage.getItem('userForm'))
  userForm.money += money.value

  // 更新远程数据
  await updateAPI(tableName, userForm)
  // 更新本地数据
  ruleForm.value.money = userForm.money
  // 更新本地localStorage数据
  localStorage.setItem('userForm', JSON.stringify(userForm))
  // 更新个人信息
  infoEvent()

  // 更新充值记录
  let role = roleList.find(item => item.tableName === tableName)?.roleName || '未知用户'
  let data = {
    amount: money.value,
    userid: localStorage.getItem('userid'),
    username: localStorage.getItem('username'),
    role,
  }
  addAPI('chargerecord', data)

  ElMessage.success('余额充值成功')
  moneyVisible.value = false
}

</script>

<template>
  <div class="center-wrapper">
    <el-form class="editform" :model="ruleForm" :rules="rules" ref="ruleFormRef" @submit.prevent>
      <el-form-item
        v-for="column in columns"
        :key="column.columnName"
        :prop="column.columnName"
        :label="column.comments"
        v-show="!column.form_hidden"
      >
        <component
          :is="column.form_type"
          :columns="columns"
          :column="column"
          :ruleForm="ruleForm"
          :tableName="tableName"
          :disabled="disabledColumnNames.includes(column.columnName)"
        />
      </el-form-item>

      <div class="btn-wrapper">
        <!-- 确认 -->
        <div class="submit-box">
          <el-button :loading="isLoading" class="submit-btn" @click="okEvent">提交</el-button>
        </div>

        <div class="cancel-box">
          <el-button class="cancel-btn" @click="openMoneyDialog">余额充值</el-button>
        </div>
      </div>
    </el-form>

    <!-- 余额充值 -->
    <el-dialog
      class="yy-dialog"
      v-model="moneyVisible"
      title="余额充值"
      destroy-on-close
      :close-on-click-modal="false"
    >
      <Pay @payOk="moneyPayOkEvent" v-model="moneyVisible">
        <div class="moneyinput-wrapper">
          <span class="label">充值金额:</span>
          <el-input-number v-model="money" :min="1" :step="1" />
        </div>
      </Pay>
    </el-dialog>

  </div>
</template>
<style>
.moneyinput-wrapper {
  width: 100%;
  display: flex;
  align-items: center;
  .label {
    margin-right: 20px;
    flex: 0 0 auto;
  }
  .el-input-number {
    width: 200px;
  }
  .el-input {
    width: 200px;
  }
}
</style>
