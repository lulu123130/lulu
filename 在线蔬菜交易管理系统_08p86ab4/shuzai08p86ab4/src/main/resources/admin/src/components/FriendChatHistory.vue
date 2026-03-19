<script setup>
import '@/style/friend.scss'
import { ref, shallowRef, watch } from 'vue'
import { getChatHistoryAPI } from '@/api/common'
import { getFirstFilePath } from '@/utils/getFilePath'
import { deleteAPI, getListAPI } from '@/api/list'
import FriendChat from './FriendChat.vue'

defineOptions({
  inheritAttrs: false,
})
const { data } = defineProps(['data'])

const isLoading = ref(false)
const list = ref([])
fetchData()
async function fetchData() {
  isLoading.value = true
  try {
    let userid = Number(localStorage.getItem('userid'))
    let res = await getChatHistoryAPI(userid)
    res.data.list.forEach(item => {
      let { type, content, picture } = item
      item.favatar = getFirstFilePath(picture)
      item.content = parseContent(content)
    })
    list.value = res.data.list
    
    data && data.updateNotreadnum()
  } catch (error) {}
  isLoading.value = false
}

// 如果是媒体类型，不显示链接，显示 字符串
let regImg = /\.png$|\.jpg$|\.jpge$|\.gif$|\.webp$/
function parseContent(content) {
  let regUrl = /^upload\//
  let isUrl = regUrl.test(content)
  if (isUrl) {
    let fileName = content.split('|')[1]
    return regImg.test(content) ? '[图片] ' + fileName : '[文件] ' + fileName
  }
  return content
}

// 删除记录
function removeEvent(item) {
  ElMessageBox.confirm('确认删除聊天记录?', '提示', {
    confirmButtonText: '删除',
    cancelButtonText: '取消',
    type: 'warning',
  })
    .then(async () => {
      isLoading.value = true
      try {
        let { uid, fid } = item
        let limit = 10000
        // 1 删除chatmessage聊天记录
        let p1 = getListAPI('chatmessage', {
          limit,
          uid,
          fid,
        })

        let p2 = getListAPI('chatmessage', {
          limit,
          uid: fid,
          fid: uid,
        })

        // 2 删除friend聊天记录
        let p3 = getListAPI('friend', {
          limit,
          uid: fid,
          fid: uid,
          type: 0,
        })
        let p4 = getListAPI('friend', {
          limit,
          uid,
          fid,
          type: 2,
        })
        let p5 = getListAPI('friend', {
          limit,
          uid: fid,
          fid: uid,
          type: 2,
        })

        let [r1, r2, r3, r4, r5] = await Promise.all([p1, p2, p3, p4, p5])

        let chatmessage_ids = [
          ...r1.data.list.map(item => item.id),
          ...r2.data.list.map(item => item.id),
        ]
        let friend_ids = [
          ...r3.data.list.map(item => item.id),
          ...r4.data.list.map(item => item.id),
          ...r5.data.list.map(item => item.id),
        ]
        await deleteAPI('chatmessage', chatmessage_ids)
        await deleteAPI('friend', friend_ids)

        fetchData()
        ElMessage.success('删除成功')
      } catch (error) {}
      isLoading.value = false
    })
    .catch(() => {})
}

// ----------------------------------
// ---------- 弹框 ---------------
// ----------------------------------
const dialogVisible = ref(false)
const dialogTitle = ref('弹框标题')
const dialogComponent = shallowRef(null)
let dialogData = {}
function toChat(row) {
  let { fid, tablename, name, favatar } = row
  dialogTitle.value = name
  dialogComponent.value = FriendChat
  dialogData = {
    fid,
    favatar,
    fname: name,
    ftablename: tablename,
  }
  dialogVisible.value = true
}
watch(
  () => dialogVisible.value,
  () => {
    // 关闭前刷新下数据
    if (!dialogVisible.value) {
      fetchData()
    }
  }
)
</script>
<template>
  <div class="friendchat-view" v-loading="isLoading">
    <div v-for="item in list" :key="item.id" class="item" @click="toChat(item)">
      <div class="left">
        <img :src="item.favatar" draggable="false"/>
        <div class="info">
          <div>{{ item.name }}</div>
          <div class="content">{{ item.content }}</div>
        </div>
      </div>
      <div class="right">
        <el-button  size="small" type="danger" @click.stop="removeEvent(item)">
          删除
        </el-button>
      </div>

      <div class="notreadnum" v-if="item.notreadnum">{{ item.notreadnum }}</div>
    </div>
    <div v-if="!list.length" class="notip">没有数据</div>

    <!-- 弹框公用 -->
    <el-dialog
      class="yy-dialog"
      v-model="dialogVisible"
      :title="dialogTitle"
      destroy-on-close
      :close-on-click-modal="false"
    >
      <component v-model="dialogVisible" :is="dialogComponent" :data="dialogData" />
    </el-dialog>
  </div>
</template>
