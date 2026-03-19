<script setup>
import '@/style/chat.scss'

import { onBeforeUnmount, ref } from 'vue'
import { BubbleList, Sender } from '@/components/chat/chatCom'

import { addAPI, getListAPI, saveAPI, getPageAPI } from '@/api/list'
import getFilePath, { clearFilePath, getFirstFilePath } from '@/utils/getFilePath'
import base from '@/utils/base'
import { closeWebSocket, initWebSocket, websocketSend } from '@/utils/webSocket'
import dayjs from 'dayjs'
import { getChatMessageListAPI } from '@/api/common'

const baseUrl = base.get().url
const action = baseUrl + 'file/upload'
const headers = {
  Token: localStorage.getItem('Token'),
}

let { data } = defineProps(['data'])
let { fid, favatar, fname, ftablename } = data

let uid = Number(localStorage.getItem('userid'))
let uimage = localStorage.getItem('useravatar')
let avatar = getFirstFilePath(uimage)
let uname = localStorage.getItem('username')

const tableName = 'chatmessage'
const config_Friend = {
  role: 'friend',
  placement: 'start',
  shape: 'corner',
  variant: 'filled',
  isMarkdown: true,
  isFog: false,
  typing: false,
  avatar: favatar,
  avatarSize: '36px',
}

const config_user = {
  role: 'user',
  placement: 'end',
  shape: 'corner',
  variant: 'outlined',
  isMarkdown: true,
  typing: false,
  avatar,
  avatarSize: '36px',
}

const list = ref([])
fetchData()
async function fetchData() {
  let res = await getChatMessageListAPI(uid, fid)

  let lastTime
  let newList = res.data.list.map(item => {
    let { id, content, format: type } = item
    let time = handleTime(lastTime, item.addtime)
    lastTime = item.addtime

    // 自己
    if (uid == item.uid) {
      return {
        key: id,
        time,
        content: compileMedia(type, content),
        ...config_user,
      }
    }
    // 好友
    return {
      key: id,
      time,
      content: compileMedia(type, content),
      ...config_Friend,
    }
  })
  list.value = newList
}
function handleTime(lastTime, currentTime) {
  let currentTime_dayjs = dayjs(currentTime)
  let lastTime_dayjs = lastTime ? dayjs(lastTime) : null
  let time = ''
  const isToday = currentTime_dayjs.isSame(dayjs(), 'day')

  time = isToday
    ? currentTime_dayjs.format('HH:mm')
    : currentTime_dayjs.format('YYYY年MM月DD日 HH:mm')

  if (!lastTime_dayjs) {
    return time
  }

  // 距离上一条5分钟内的、连续的，不显示
  // 计算时间差（分钟），取绝对值
  const diffInMinutes = Math.abs(lastTime_dayjs.diff(currentTime_dayjs, 'minute'))
  // 判断是否在 5 分钟以内
  if (diffInMinutes <= 5) {
    time = null
  }
  return time
}

// ----------------------------------
// ---------- 输入框 ---------------
// ----------------------------------
const senderRef = ref()
const senderValue = ref('')
const senderLoading = ref(false)
const uploadRef = ref(null)
// 发送
async function sendMsg(type = 1, content) {
  senderLoading.value = true
  try {
    // 文本和表情
    let data = {
      uid,
      fid,
      content,
      format: type,
    }

    await addAPI(tableName, data)

    websocketSend(content)
    // [2] 更新聊天记录
    fetchData()
    updateHistory()

    type = 1 && (senderValue.value = '')
  } catch (error) {}
  senderLoading.value = false
}
// 更新friend表(聊天记录表)
async function updateHistory() {
  let tableName_friend = 'friend'
  let res = await getPageAPI(tableName_friend, {
    uid,
    fid,
  })
  if (res.data.total > 0) {
    return
  }
  // 没有聊天记录，双方各添加一条
  let uData = {
    uid,
    fid,
    name: fname,
    tablename: ftablename,
    picture: clearFilePath(favatar),
    type: 2,
  }
  let fData = {
    uid: fid,
    fid: uid,
    name: uname,
    tablename: localStorage.getItem('sessionTable'),
    picture: uimage,
    type: 2,
  }
  addAPI(tableName_friend, uData)
  addAPI(tableName_friend, fData)
}
// 上传文件
function openFileDialog() {
  uploadRef.value.click()
}
function fileUploadSuccess(res, file, fileList) {
  let type = 2,
    fileName = file.raw.name,
    fileUrl = 'upload/' + res.file,
    content = fileUrl + '|' + fileName,
    fileType = file.raw?.type || ''

  switch (true) {
    case fileType.startsWith('image'):
      // 图片
      type = 2
      break
    case fileType.startsWith('video'):
      // 视频
      type = 3
      break
    case fileType.startsWith('audio'):
      // 音频
      type = 6
      break

    default:
      // 文件
      type = 4
      break
  }

  sendMsg(type, content)
}
// 解析视频/文件/图片 格式的内容
function compileMedia(type, content) {
  let newContent, fileUrl, fileName
  if (type != 1) {
    let list = content.split('|')
    fileName = list[1]
    fileUrl = getFilePath(list[0])
  }
  switch (type) {
    case 1:
      newContent = content
      break

    case 2:
      newContent = `<img width="200px" src="${fileUrl}" alt="${fileName}"/>`
      break

    case 3:
      newContent = `<video controls width="200px"><source src="${fileUrl}" ></video>`
      break

    case 4:
      newContent = `[${fileName}](${fileUrl})`
      break

    case 6:
      newContent = `<audio controls width="200px" src="${fileUrl}"></audio>`
      break

    default:
      newContent = content
      break
  }

  return newContent
}

// ----------------------------------
// ---------- websocket -------------
// ----------------------------------

initWebSocket(fid, uid, fetchData)
onBeforeUnmount(() => {
  closeWebSocket()
})
</script>
<template>
  <div class="chat-wrapper">
    <BubbleList :list="list">
      <template #header="{ item }">
        <div class="header">
          <span class="time" v-if="item.time">
            {{ item.time }}
          </span>
        </div>
      </template>
    </BubbleList>
    <Sender
      ref="senderRef"
      v-model="senderValue"
      :loading="senderLoading"
      @submit="sendMsg(1, senderValue)"
    >
      <template #prefix>
        <div class="prefix-self-wrap">
          <el-button @click="openFileDialog">
            <el-icon><Link /></el-icon>
          </el-button>
        </div>
      </template>
    </Sender>

    <el-upload
      style="display: none"
      :action="action"
      :headers="headers"
      :on-success="fileUploadSuccess"
      :show-file-list="false"
    >
      <span ref="uploadRef"></span>
    </el-upload>
  </div>
</template>
