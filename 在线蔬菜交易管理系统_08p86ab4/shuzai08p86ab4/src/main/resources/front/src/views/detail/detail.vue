<script setup>
import '@/style/detail.scss'

import { ref, shallowRef, provide, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'

import '@/components/DetailItem/index.js'
import '@/components/TabItem/index.js'

// 复用 列表list.vue的面包屑、热门
import BreadCrumb from '../list/BreadCrumb.vue'
import Hot from '../list/Hot.vue'

import Buttons from './Buttons.vue'
import Custom from './Custom.vue'
import DetailList from './DetailList.vue'
import Pictures from './Pictures.vue'
import Tabs from './Tabs.vue'
import ThumbsUp from './ThumbsUp.vue'
import Videos from './Videos.vue'
import ListEdit from '../list/ListEdit.vue'
import Review from '../list/components/Review.vue'
import FriendChat from '@/components/FriendChat.vue'

import tableConfigs from '@/utils/tableConfigs'
import {
  addAPI,
  deleteAPI,
  getDetailAPI,
  getListAPI,
  getPageAPI,
  saveAPI,
  updateAPI,
} from '@/api/list'
import {
  alipayAPI,  
  getGroupAPI
} from '@/api/common'

import { getAutoSort1API } from '@/api/front'
import getUiList from '@/utils/getUiList'
import { toDetailPage, toListPage } from '@/utils/navigate'
import getFilePath, { getFilePaths, getFirstFilePath } from '@/utils/getFilePath'
import { isAuth } from '@/utils/auth'
import dayjs from 'dayjs'
import { getAvatar, getNanoId } from '@/utils'
import { 
  csuEvent,
} from '@/utils/feature'

let { tableName } = defineProps(['tableName'])
let tableConfig = tableConfigs[tableName]
let { table, columns, detailConfig } = tableConfig
let {
  imgName,
  imgNames,
  titleName,
  inteltypeName,
  videoNames,
  merchantName,
  actionBtns,
  tabs,
  hasIsanon,
} = detailConfig
let { hasHot, comments, storeUp, thumbsUp, vip, virtualPay, discuss, hasChapter } = table
hasHot = hasHot == '详' || hasHot == '全'
let storeUpConfig = {
  storeUp,
  imgName,
  titleName,
  inteltypeName,
}
const route = useRoute()
const router = useRouter()
const { id, centerType } = route.query

// ----------------------------------
// ---------- 面包屑 ----------------
// ----------------------------------
const homePath = ref('/index/home')
const second = ref({
  path: centerType
    ? '/index/' + tableName +'?centerType=' + centerType
    : '/index/' + tableName,
  title: comments,
})
const lastTitle = computed(() => {
  return data.value[titleName] || '详情'
})
function backEvent(){
  router.back()
}
// ----------------------------------
// ------------ 列表 ----------------
// ----------------------------------
let detailColumns = ref(getDetailColumns())
function getDetailColumns() {
  let list = columns.filter(column => column.detail_type)

  // 前台访问，过滤审核字段
  if (!centerType) {
    let columnNameList_sfsh = ['sfsh', 'shhf']
    list = list.filter(column => !columnNameList_sfsh.includes(column.columnName))
  }


  list.sort((a, b) => b.detail_order - a.detail_order)
  return list
}

const hasThumbsUp = false

// ----------------------------------
// ---------- 标签页 ----------------
// ----------------------------------
const tabActiveName = ref(0)

// ----------------------------------
// ----------- 热门信息 --------------
// ----------------------------------
const hotData = ref({
  comments: '热门信息',
  tableName,
  subtitle: 'Hot',
  list: [],
})
getHotList()
async function getHotList() {
  if (!hasHot) {
    return
  }
  let apiParams = {
    limit: 6,
  }
  let res = await getAutoSort1API(tableName, apiParams)
  hotData.value.list = getUiList(detailConfig, res.data.list)
}

// ----------------------------------
// ---------- 弹框 ---------------
// ----------------------------------
const dialogVisible = ref(false)
const dialogTitle = ref('弹框标题')
const dialogComponent = shallowRef(null)
const dialogClass = ref('')
let dialogData = {}
function openDialog(data) {
  dialogTitle.value = data.dialogTitle
  dialogComponent.value = data.dialogComponent
  dialogData = data.dialogData
  dialogClass.value = data.dialogClass

  dialogVisible.value = true
}

// ----------------------------------
// ---------- 操作按钮 ---------------
// ----------------------------------
// 鉴权
let actionButtons = ref(actionBtns.filter(item => item.isPublic || isAuth(tableName, item.name, !centerType)))
const actionEventMap = {
  remove,
  edit,
  chat,
  contact,
  sfsh,
}
const actionEvent = button => {
  let { key, type, title } = button

  // [1] 优先判断是否 跨表功能的按钮
  if (type === 'crossTable') {
    return crossTableHander(button, data.value)
  }

  // [2] 根据key执行对应方法
  if (actionEventMap[key]) {
    return actionEventMap[key](button, data.value)
  }

  ElMessage.info(`【${title}】该功能暂时未添加`)
}

// ----------------------------------
// ------------ 删除 ----------------
// ----------------------------------
// 删除单个
function remove(button, row) {
  ElMessageBox.confirm('确认删除?', '提示', {
    confirmButtonText: '删除',
    cancelButtonText: '取消',
    type: 'warning',
  })
    .then(async () => {
      let ids = [row.id]
      await deleteAPI(tableName, ids)
      ElMessage.success('删除成功')
      toListPage(tableName, centerType)
    })
    .catch(() => {})
}

// ----------------------------------
// ---------- 编辑 ---------------
// ----------------------------------
function edit(button, row) {
  if (table.sfsh === '是' && (row.sfsh == '是' || row.sfsh == '否')) {
    ElMessage.error('已审核，不能修改了。')
    return
  }
  dialogTitle.value = button.title
  dialogClass.value = ''
  dialogComponent.value = ListEdit
  dialogData = {
    type: 'update',
    id: row.id,
    tableName,
    row,
    okText: '提交',
    cancleText: '取消',
  }
  dialogVisible.value = true
}

// ----------------------------------
// ---------- 跨表功能 ---------------
// ----------------------------------
async function crossTableHander(button, row) {
  let { name, crossType } = button
  let tableConfig = tableConfigs[tableName]
  let { table, columns } = tableConfig
  let { sfsh, hasPay, isReverse, virtualPay } = table
  let index = table.crossOptButton.findIndex(buttonName => buttonName === name)
  // 跨表的关联数据
  // 审核权限
  let crossOptAudit = table.crossOptAudit[index]
  // 支付权限
  let crossOptPay = table.crossOptPay[index]
  // 已经存在的提示
  let tips = table.crossOptButtonTips[index]
  // 关联columnName列名：比如reservationstate。
  let statusColumnName = table.crossOptButtonStatusColumns[index]
  // 库表的新表
  let newTableName = table.crossOptTableName[index]


  // [1] 退出条件判断

  // 1.1 已开启审核功能，且未审核状态下
  if (sfsh == '是' && crossOptAudit === '是' && row.sfsh != '是') {
    ElMessage.info('请审核通过后再操作')
    return
  }


  // 1.3 倒计时
  if (isReverse == '是' && virtualPay != '是') {
    if (dayjs().isAfter(dayjs(row.reversetime))) {
      ElMessage.info('倒计时已结束')
      return
    }
  }


  // 1.5 statusColumnName 次数/状态限制 字段，
  // 1.5.1 状态限制 reservationstate
  // 1.5.2 次数限制 [1]
  let statusColumnValue
  let isLimit = false
  if (statusColumnName) {
    if (statusColumnName.startsWith('[')) {
      isLimit = true
      // 限制次数   从 [1] 提取 次数 1
      let limitNum = statusColumnName.replace(/\[|\]/g, '')
      limitNum = Number(limitNum)

      // 查询当前次数
      let params = {
        crossrefid: row.id,
        crossuserid: Number(localStorage.getItem('userid')),
      }
      // inspectiontime 日期条件
      let hasInspectiontime = tableConfigs[newTableName].columns.some(column => column.columnName == 'inspectiontime')
      if (hasInspectiontime) {
        let inspectiontime = dayjs().format('YYYY-MM-DD')
        params.inspectiontimestart = inspectiontime
        params.inspectiontimeend = inspectiontime
      }
      let res = await getPageAPI(newTableName, params)
      if (res.data.total >= limitNum) {
        ElMessage.error(tips)
        return
      }
    } else {
      // 状态限制
      let { customize } = columns.find(column => column.columnName === statusColumnName)
      // 关联的字段，约定是单选类型、且取的是选项一的值 customize: '已取消,已预约'-> statusColumnValue: '已取消'
      statusColumnValue = customize.split(',')[0]
      if (row[statusColumnName] === statusColumnValue) {
        ElMessage.success(tips)
        return
      }
    }
  }

  switch (crossType) {
    case 1:
      // 弹出编辑框
      dialogTitle.value = button.title
      dialogClass.value = ''
      dialogComponent.value = ListEdit
      dialogData = {
        type: 'cross',
        id: row.id,
        tableName: newTableName,
        crossData: {
          crossType,
          isLimit,
          statusColumnName,
          statusColumnValue,
          oldRow: row,
          oldTableName: tableName,
          newTableName,
          okText: '提交',
          cancleText: '取消',
        },
      }

      dialogVisible.value = true
      break


  }
}


// ----------------------------------
// ---------- 审核功能 ---------------
// ----------------------------------
// 审核
function sfsh(button, row) {
  if (row.sfsh == '是' || row.sfsh == '否') {
    ElMessage.error('已审核了')
    return
  }


  dialogClass.value = ''
  dialogComponent.value = Review
  dialogTitle.value = '审核'
  dialogData = {
    datas: [row],
    isSHMode: true,
    columns,
    comments,
  }
  dialogVisible.value = true
}


// ----------------------------------
// ---------- 虚拟支付 ---------------
// ----------------------------------
let { payBtns, hasBuyInput } = detailConfig
const payButtons = ref(payBtns)
const buynumber = ref(1)
const payLoading = ref(false) // 支付按钮loading
async function shopEvent(key) {
  payLoading.value = true

  try {
    // 1. 登录
    if (!localStorage.getItem('Token')) {
      throw new Error('请先登录')
    }

    // 2. 倒计时
    if (table.isReverse == '是' && dayjs().isAfter(dayjs(data.value.reversetime))) {
      throw new Error('倒计时已结束')
    }

    // 3. 单限
    if (table.limit == '是') {
      if (buynumber.value > data.value.onelimittimes) {
        throw new Error('每单数量限制最多' + data.value.onelimittimes)
      }
      if (buynumber.value > data.value.alllimittimes) {
        throw new Error('库存不足')
      }
    }

    let keyFnMap = {
      buyBtn,      
      cartBtn,
  }

    if (keyFnMap[key]) {
      await keyFnMap[key]()
    }
  } catch (error) {
    ElMessage.error(error.msg || error.message || '未知原因')
  }
  payLoading.value = false
}
// 加入购物车
async function cartBtn() {
  let userid = Number(localStorage.getItem('userid'))
  let goodid = data.value.id
  let tableName_cart = 'cart'

  // 1. 判断是否已经在购物车
  let res = await getListAPI(tableName_cart, {
    userid,
    tablename: tableName,
    goodid,
  })
  let isInCart = res.data.total > 0
  if (isInCart) {
    ElMessage.error('已经在购物车')
    return
  }

  // 2. 加到购物车表
  let cartData = {
    goodid,
    userid,
    tablename: tableName,
    buynumber: buynumber.value,
    goodname: data.value[titleName],
    picture: data.value[imgName],
    price: data.value.price,
  }

  // 多商户配置 的 商户名称的字段名 merchantName
  if (merchantName) {
    cartData[merchantName] = data.value[merchantName]
  }

  // 智能推荐字段
  if (inteltypeName) {
    cartData.goodtype = data.value[inteltypeName]
  }


  await saveAPI(tableName_cart, cartData)
  ElMessage.success('加入购物车成功')
}

// 通用购买
async function buyBtn() {
  let type = 1
  let userid = Number(localStorage.getItem('userid'))
  let goodid = data.value.id
  // 生成订单
  let orderData = {
    tablename: tableName,
    userid,
    goodid,
    goodname: data.value[titleName],
    picture: data.value[imgName],
    buynumber: buynumber.value,
    price: data.value.price,
    total: Number(Number(data.value.price * buynumber.value).toFixed(2)),
    type,
    role: localStorage.getItem('sessionTable'),
  }

  // 多商户配置 的 商户名称的字段名 merchantName
  if (merchantName) {
    orderData[merchantName] = data.value[merchantName]
  }

  // 智能推荐字段
  if (inteltypeName) {
    orderData.goodtype = data.value[inteltypeName]
  }


  router.push({
    path: '/index/orderConfirm',
    state: {
      orderDatas: [orderData],
      type,
    },
  })
}





// ----------------------------------
// ---------- 聊天功能 ---------------
// ----------------------------------
// 私聊(用户表)
async function chat(button, row) {
  let userid = Number(localStorage.getItem('userid'))
  if (!userid) {
    ElMessage.error('请先登录')
    return
  }

  if (userid == row.id) {
    ElMessage.error('不能给自己发信息')
    return
  }

  let fid = row.id
  let [favatar, fname] = getAvatar(row, tableName)

  // 打开弹窗
  dialogTitle.value = fname
  dialogClass.value = ''
  dialogComponent.value = FriendChat
  dialogData = {
    fid,
    favatar,
    fname,
    ftablename: tableName,
  }
  dialogVisible.value = true
}
// 联系TA
async function contact(button, row) {
  let userid = Number(localStorage.getItem('userid'))
  let sessionTable = localStorage.getItem('sessionTable')
  if (!userid) {
    ElMessage.error('请先登录')
    return
  }

  let { friendTableNames, friendNames, columnNames } = button
  // 多个，优先找不同表
  let index = friendTableNames.findIndex(item => item != sessionTable)
  if (index == -1) {
    index = 0
  }
  let friendTableName = friendTableNames[index],
    friendName = friendNames[index],
    columnName = columnNames[index]

  // 确定对方id
  let res = await getListAPI(friendTableName, {
    limit: 1,
    [friendName]: row[columnName],
  })
  let friendInfo = res.data.list[0]
  if (!friendInfo) {
    ElMessage.error('没有找到对方信息')
    return
  }
  let fid = friendInfo.id

  if (userid === fid) {
    ElMessage.error('不能给自己发信息')
    return
  }
  let [favatar, fname] = getAvatar(friendInfo, friendTableName)

  // 打开弹窗
  dialogTitle.value = friendInfo[friendName]
  dialogClass.value = ''
  dialogComponent.value = FriendChat
  dialogData = {
    fid,
    favatar,
    fname,
    ftablename: friendTableName,
  }
  dialogVisible.value = true
}


// ----------------------------------
// ----------- 图片 --------------
// ----------------------------------
const banners = ref([])
const showBanner = ref(false)
function getPicture() {
  if (!imgName) return
  let list = []
  imgNames.forEach(columnName => {
    let urls = getFilePaths(data.value[columnName])
    urls.forEach(picture => {
      list.push({
        id: picture,
        picture,
        columnName
      })
    })
  })
  banners.value = list
  showBanner.value = true
}
let previewSrc = []
const showPreview = ref(false)
let initialIndex = 0
function bannerEvent(item) {
  initialIndex = banners.value.findIndex(banner => banner.id == item.id)
  previewSrc = banners.value.map(banner => banner.picture)
  showPreview.value = true
}

// ----------------------------------
// ----------- 视频 --------------
// ----------------------------------
const videoList = ref([])
function getVideo() {
  let list = []
  let errorMsg = '请先开通会员'
  let userForm = JSON.parse(localStorage.getItem('userForm'))

  let videoColumns = columns.filter(column => column.type == '视')
  videoColumns.forEach(column => {
    let { columnName, formatValidation } = column

    let canPlay = true
    if (formatValidation == '会员') {

      // 未登录
      if (!userForm) {
        canPlay = false
      }

      // 非会员 + 自己的 + 带支付
      if (canPlay && userForm.vip != '是' && centerType && data.value.ispay == '未支付') {
        canPlay = false
      }

      // 非会员 + 不是自己的
      if (canPlay && userForm.vip != '是' && !centerType) {
        canPlay = false
      }

    }
    let srcList = getFilePaths(data.value[columnName])
    let list2 = srcList.map(src => {
      return {
        src,
        canPlay,
        errorMsg,
      }
    })
    list = list.concat(list2)
  })

  videoList.value = list
}




// ----------------------------------
// ----------- 数据 -----------------
// ----------------------------------
const data = ref({})
fetchData()
async function fetchData() {
  let res = await getDetailAPI(tableName, id)
  data.value = res.data

  // 图片
  getPicture()

  // 视频
  getVideo()



}

function detailEvent(tableName, id) {
  toDetailPage(tableName, id, centerType)
}

function moreEvent(tableName) {
  toListPage(tableName, centerType)
}

provide('detail', {
  id,
  
  homePath,
  second,
  lastTitle,
  backEvent,

  banners,
  showBanner,
  bannerEvent,

  data,
  detailColumns,
  tableName,
  storeUpConfig,
  merchantName,

  payButtons,
  payLoading,
  hasBuyInput,
  buynumber,
  shopEvent,
  actionButtons,
  actionEvent,

  hasThumbsUp,  

  videoList,

  tabs,
  tabActiveName,
  centerType,
  table,

  hasHot,
  hotData,
  detailEvent,
  moreEvent,

  titleName,
})

// 确保 BreadCrumb能复用 后台的
provide('header', {
  homePath,
  second,
  lastTitle,
  backEvent,

  banners,
  showBanner,
  bannerEvent,
})
// 确保 hot能复用 首页的
provide('start', {
  detailEvent,
  moreEvent,
})
</script>

<template>
  <div class="detail-page" :data-key="tableName">
        <BreadCrumb />
    <div class="left-right">
      <div class="detail-left">
        <Pictures class="pictures-box" v-if="showBanner" />
        <ThumbsUp v-if="hasThumbsUp" />
        <div class="detail-box">
          <DetailList />
          <Buttons />
        </div>
        <Videos />
        <Tabs />
        <Custom />
      </div>
      <div class="detail-right" v-if="hasHot">
        <Hot :data="hotData" />
      </div>
    </div>
    
    <!-- 弹框公用 -->
    <el-dialog
      class="yy-dialog"
      v-model="dialogVisible"
      :title="dialogTitle"
      destroy-on-close
      :close-on-click-modal="false"
    >
      <component
        v-model="dialogVisible"
        :is="dialogComponent"
        :tableName="tableName"
        :data="dialogData"
        :class="dialogClass"
        @fetchData="fetchData"
        @openDialog="openDialog"
      />
    </el-dialog>

    <!-- 图片预览 -->
    <el-image-viewer
      v-if="showPreview"
      :url-list="previewSrc"
      show-progress
      :initial-index="initialIndex"
      @close="showPreview = false"
      :teleported="true"
    />


  </div>
</template>
