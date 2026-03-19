<script setup>
/**
 * @description 个人中心
 */
import '@/style/center.scss'

// 注册表单组件
import '@/components/FormItem/index.js'
import { reactive, ref, computed, provide, shallowRef } from 'vue'

import CenterTitle from './CenterTitle.vue'
import Custom from './Custom.vue'
import Info from './Info.vue'
import Menus from './Menus.vue'
import Tabs from './Tabs.vue'
import CenterEdit from './CenterEdit.vue'
import UpdatePassword from './updatePassword.vue'

import FriendChatHistory from '@/components/FriendChatHistory.vue'
import MyDiscuss from '@/components/MyDiscuss.vue'

import tableConfigs from '@/utils/tableConfigs'
import { useUserInfo } from '@/store'
import { useRouter } from 'vue-router'
import { toListPage } from '@/utils/navigate'
import { isAuth } from '@/utils/auth'
import { getPageAPI, getListAPI } from '@/api/list'
import { getChatHistoryAPI } from '@/api/common'
import { getSessionAPI } from '@/api/login'

let router = useRouter()
// 检查用户是否登录
const userInfoStore = useUserInfo()
// if (!userInfoStore.userInfo.isLogin) {
//   router.push('/login')
// }

let tableName = localStorage.getItem('sessionTable')
let tableConfig = tableConfigs[tableName]
let { table, columns } = tableConfig

const title = '个人中心'
const userAvatar = computed(() => userInfoStore.userInfo.avatar)
// ----------------------------------
// ---------- 个人信息 ----------------
// ----------------------------------
const infoList = ref([])
infoEvent()
async function infoEvent() {
  let excludeColumnTypes = ['图', '音', '视', '文', '编'] // 媒体类型不显示
  let excludeColumnNames = [
    'passwordwrongnum',
    'mima',
    'password',
    'status',
    'money',
    'pquestion',
    'panswer',
    'sfsh',
    'shhf',
    'clicktime',
    'browseduration',
    'thumbsupnum',
    'crazilynum',
    'clicknum',
    'storeupnum',
    'discussnum',
  ] // 排除的字段名
  let centerColumns = columns
    .filter(
      item =>
        !excludeColumnTypes.includes(item.type) && !excludeColumnNames.includes(item.columnName)
    )
    .slice(0, 6) // 只显示前6个字段
  // 更新个人信息
  let { data: userForm } = await getSessionAPI(tableName)
  localStorage.setItem('userForm', JSON.stringify(userForm))

  let list = []
  centerColumns.forEach(column => {
    list.push({
      label: column.comments,
      value: userForm[column.columnName] || '',
    })
  })
  infoList.value = list
}

// ----------------------------------
// ---------- 标签页 ----------------
// ----------------------------------
const tabs = shallowRef(getTabs())
function getTabs() {
  let list = [
    {
      label: '个人中心',
      name: CenterEdit,
    },
    {
      label: '修改密码',
      name: UpdatePassword,
    },
    {
      label: '聊天记录',
      name: FriendChatHistory,
      updateNotreadnum_noFriend,
    },
  ]

  let roleMenu = JSON.parse(localStorage.getItem('roleMenu'))
  let hasMyDiscuss = roleMenu.some(item => {
    if (item.menuJump == '81') {
      return true
    }
    if (item.child) {
      return item.child.some(i => i.menuJump == '81')
    }
    return false
  })
  if (hasMyDiscuss) {
    list.push({
      label: '我的评论',
      name: MyDiscuss,
    })
  }    

  return list
}

function tabEvent(tab, event) {}

// ----------------------------------
// ---------- 菜单 ----------------
// ----------------------------------
const menuList = ref(getMenuList())
function getMenuList() {
  let list = [
    {
      label: '我的订单',
      path: '/index/shop-order/order',
    },
    {
      label: '我的收藏',
      path: '/index/storeup',
      query: { type: 1 }
    },
  ]


  const filterTableNames = [
    'orders',
    'orders/已退款',
    'orders/未支付',
    'orders/待审核',
    'orders/已取消',
    'orders/已完成',
    'orders/已发货',
    'orders/已支付',
    'storeup',
    'systemintro',
    'aboutus',
    'menu',  
  ]
  let roleMenu = JSON.parse(localStorage.getItem('roleMenu'))
  // 展开所有菜单
  roleMenu.forEach(item => {
    getMenuItem(item)
    
    if (item.child) {
      item.child.forEach(child => {
        getMenuItem(child)
      })
    }
  })

  function getMenuItem(item) {
    let { menu, tableName } = item
    // 过滤菜单
    if (!tableName) {
      return
    }

    if (tableName.startsWith('stat/')) {
      return
    }

    if (filterTableNames.includes(tableName)) {
      return
    }

    // 确定路径
    let path = '/index/' + tableName
    list.push({
      label: menu,
      tableName: tableName,
      path,
      query: { centerType: 1 },
    })
  }

  return list
}

function menuEvent(item) {
  router.push({ path: item.path, query: item.query })
}

// ----------------------------------
// ---------- 未读消息 ---------------
// ----------------------------------
updateNotreadnum_noFriend()
async function updateNotreadnum_noFriend() {
  let userid = Number(localStorage.getItem('userid'))
  let res = await getChatHistoryAPI(userid)
  let notreadnum = res.data.list.reduce((total, item) => total + (Number(item.notreadnum) || 0), 0)

  let newTabs = tabs.value.map(item => {
    return {
      ...item,
      notreadnum: item.label == '聊天记录' ? notreadnum : item.notreadnum
    }
  })
  tabs.value = newTabs
}

provide('center', {
  infoList,
  userAvatar,

  tabs,
  tabEvent,

  menuList,
  menuEvent,

  infoEvent,
})
</script>

<template>
  <div class="center-page">
    
    <CenterTitle :title="title" />
    <Info />
    <Tabs />
    <Menus />
    <Custom />
  
  </div>
</template>
