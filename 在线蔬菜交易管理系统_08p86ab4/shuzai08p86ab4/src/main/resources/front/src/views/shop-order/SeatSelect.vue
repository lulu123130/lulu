<script setup>
import SelectedImg from '@/assets/img/selected.png'
import UnActiveImg from '@/assets/img/seat-unactive.png'
import ActiveImg from '@/assets/img/seat-active.png'
import { ref } from 'vue'
defineOptions({
  inheritAttrs: false,
})
const visible = defineModel()
let { data } = defineProps(['data'])
let { detailData, okEvent } = data
let { number, selected } = detailData

const list = ref(initList())
function initList() {
  let newList = []
  let selectArray = selected ? selected.split(',').map(item => Number(item)) : []
  for (let i = 1; i <= number; i++) {
    newList.push({
      num: i,
      select: selectArray.includes(i),
      active: false,
    })
  }
  return newList
}

// 座位点击事件
function seatClickEvent(item) {
  if (item.select) {
    ElMessage.error('该座位已被预定')
    return
  }
  item.active = !item.active
}

function submitEvent() {
  let activeSeat = list.value.filter(item => item.active).map(item => item.num)
  if (!activeSeat.length) {
    ElMessage.error('请选择要预定的位置')
    return
  }

  okEvent(activeSeat)
}
</script>
<template>
  <div class="editform seat-select-wrapper">
    <div class="list">
      <div class="item" v-for="item in list" :key="item.num" @click="seatClickEvent(item)">
        <img
          v-if="item.select"
          class="common-img selected-img"
          :src="SelectedImg"
          draggable="false"
        />
        <img
          v-else-if="item.active"
          class="common-img active-img"
          :src="ActiveImg"
          draggable="false"
        />
        <img v-else class="common-img unactive-img" :src="UnActiveImg" draggable="false" />
        <div class="seatnum">{{ item.num }}号</div>
      </div>
    </div>

    <div class="btn-wrapper">
      <div class="submit-box">
        <el-button class="submit-btn" native-type="submit" @click="submitEvent">确定</el-button>
      </div>

      <div class="cancel-box">
        <el-button class="cancel-btn" @click="visible = false">取消</el-button>
      </div>
    </div>
  </div>
</template>

<style>
.seat-select-wrapper {
  .list {
    width: 100%;
    display: grid;
    grid-template-columns: repeat(6, 1fr);
    .item {
      display: flex;
      flex-direction: column;
      text-align: center;
    }

    .common-img {
      width: 100%;
      height: 40px;
      object-fit: contain;
    }

    .seatnum {
      line-height: 20px;
    }
  }
}
</style>
