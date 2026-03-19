<script setup>
/**
 * @description 新闻
 *
 * data.title 标题
 * data.subtitle 副标题
 * data.tableName 表名
 * data.list 列表数据
 *
 * item.titles 标题列表,可能多个标题
 * item.img 图片
 * item.user 发布人,可能没有 undefined判断
 * item.addtime 发布时间
 * item.thumbsupnum 点赞,可能没有 undefined判断
 * item.storeupnum 收藏,可能没有 undefined判断
 * item.clicknum 查看量,可能没有 undefined判断
 * item.browseduration 浏览时长,可能没有 undefined判断
 *
 * item.introduction 简介
 *
 * detailEvent 点击详情事件
 * moreEvent 更多事件
 */
import { inject, ref, watch } from 'vue'
import dayjs from 'dayjs'

let { data } = defineProps(['data'])
let { detailEvent, moreEvent } = inject('start')

const actId = ref(null)
watch(
  () => data.list,
  () => {
    actId.value = data.list[2]?.id
  }
)
function textEvent(item) {
  actId.value = item.id
}
</script>

<template>
  <div class="news-default">
    <div class="inner">
      <div class="header">
        <div class="title">{{ data.comments }}</div>
        <div class="subtitle">{{ data.subtitle }}</div>
        <el-button icon="More" circle class="more" type="success" @click="moreEvent(data.tableName)"></el-button>
      </div>

      <div class="content">
        <div class="left">
          <div class="item" v-for="item in data.list.slice(0, 2)" :key="item.id" @click="detailEvent(data.tableName, item.id)">
            <div class="img-box">
              <img :src="item.img" draggable="false" />
            </div>
            <div class="info-box">
              <div v-for="title in item.titles" class="text">{{ title }}</div>
              <div class="time">
                <span>发布时间:</span>
                <span>{{ dayjs(item.addtime).format('YYYY-MM-DD') }}</span>
              </div>
              <div class="introduction">{{ item.introduction }}</div>
            </div>
          </div>
        </div>
        <div class="right">
          <div class="item" v-for="item in data.list.slice(2, 6)" :key="item.id" @click="detailEvent(data.tableName, item.id)" :class="actId == item.id ? 'act' : ''">
            <div class="text-box" @click.stop="textEvent(item)">
              <div v-for="title in item.titles" class="text">{{ title }}</div>
            </div>
            <div class="introduction">{{ item.introduction }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style>
.news-default {
  width: 100%;

  .header {
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    position: relative;
    flex-direction: column;
    &::before {
      content: '';
      width: 100%;
      height: 22px;
      background: url(http://codegen.caihongy.cn/20250926/dc4e0caa1a6b40979ba5998b2310daa9.png);
      position: absolute;
      bottom: 0;
    }
    .title {
      font-size: 28px;
      color: var(--btn-bg-color-);
      padding: 0 50px;
      margin: 0 0 10px;
      z-index: 3;
      font-weight: 600;
    }
    .subtitle {
      font-size: 18px;
      color: #ccc;
      padding: 0 20px;
      margin: 0;
      z-index: 2;
    }
    .more {
      position: absolute;
      right: 0;
      bottom: 16px;
    }
  }
  .content {
    width: 100%;
    padding: 20px;
    display: flex;
    justify-content: space-between;
    gap: 30px;
    & > div {
      flex: auto;
      width: 0;
    }
    .item {
      width: 100%;
      height: auto;
      position: relative;
      cursor: pointer;
    }
  }
  .left {
    .item {
      display: flex;
      gap: 20px;
      padding-bottom: 20px;
      border-bottom: 1px dashed rgb(187, 187, 187);
      &:hover {
        img {
          transform: scale(1.08);
        }
        .info-box .text {
          color: var(--btn-bg-color-);
        }
      }
    }
    .item:nth-of-type(1) {
      margin-bottom: 20px;
    }
    .img-box {
      flex: none;
      width: 185px;
      height: 150px;
      overflow: hidden;
      img {
        width: 100%;
        height: 100%;
        object-fit: cover;
        transition: transform linear 0.3s;
      }
    }

    .info-box {
      flex: auto;
      width: 0;
      .text {
        width: 100%;
        color: #000000;
        font-size: 18px;
        font-weight: 600;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
      }
      .time {
        color: rgb(133, 133, 133);
        font-size: 12px;
        padding: 10px 0;
      }
      .introduction {
        color: rgb(133, 133, 133);
        font-size: 14px;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 4;
        overflow: hidden;
        text-overflow: ellipsis;
      }
    }
  }

  .right {
    .act {
      .text-box {
        background-color: var(--btn-bg-color-);
        color: var(--btn-font-color-);
        &::after {
          content: '+';
          color: var(--btn-font-color-);
        }
      }
      .introduction {
        padding-top: 20px;
        height: 150px;
        opacity: 1;
        overflow: hidden;
      }
    }
    .text-box {
      position: relative;
      color: rgb(53, 53, 53);
      font-weight: normal;
      padding: 10px 50px 10px 25px;
      font-size: 15px;
      cursor: pointer;
      .text {
        line-height: 24px;
        height: 24px;
      }
      &::after {
        content: '-';
        color: var(--btn-bg-color-);
        font-size: 22px;
        font-weight: normal;
        line-height: 40px;
        position: absolute;
        right: 25px;
        top: 0px;
      }
    }
    .introduction {
      font-size: 14px;
      color: rgb(144, 144, 144);
      padding: 0px 25px;
      margin: 0px;
      height: 0px;
      display: block;
      overflow: hidden;
      transition: 0.5s;
      opacity: 0;
    }
  }
}
</style>

