<script setup>
/**
 * @description 列表
 *
 * data.title 标题
 * data.subtitle 副标题
 * data.tableName 表名
 * data.list 单个列表的数据
 * data.hasMusic 音乐
 * data.musicEvent 音乐点击事件
 *
 * item.picture 图片
 * item.titles 标题列表,可能多个标题
 * item.img 价格,可能没有 undefined判断
 * item.user 发布人,可能没有 undefined判断
 * item.addtime 发布时间
 * item.thumbsupnum 点赞,可能没有 undefined判断
 * item.storeupnum 收藏,可能没有 undefined判断
 * item.clicknum 查看量,可能没有 undefined判断
 * item.browseduration 浏览时长,可能没有 undefined判断
 *
 * hideHeader 隐藏头部
 * detailEvent 点击详情事件
 * moreEvent 更多事件
 */
import { inject } from 'vue'
import dayjs from 'dayjs'

let { data, hideHeader } = defineProps(['data', 'hideHeader'])
let { detailEvent, moreEvent } = inject('start')
</script>

<template>
  <div class="list-default">
    <div class="inner">
      <div class="header" v-if="!hideHeader">
        <div class="title">{{ data.comments }}</div>
        <div class="subtitle">{{ data.subtitle }}</div>
        <el-button icon="More" circle class="more" type="success" @click="moreEvent(data.tableName)"></el-button>
      </div>

      <div class="content">
        <div class="item" v-for="item in data.list" :key="item.id" @click="detailEvent(data.tableName, item.id)">
          <div class="img-box" v-if="item.img || data.hasMusic">
            <img
              v-if="data.hasMusic"
              class="music"
              src="http://codegen.caihongy.cn/20251230/096adf8336ff4c9397450b81c24cc10f.png"
              @click.stop="data.musicEvent(item)"
            />
            <img v-if="item.img" class="cover" :src="item.img" draggable="false" />
          </div>
          <div class="info-box">
            <div v-for="title in item.titles" class="text">{{ title }}</div>
            <div v-if="item.price !== undefined" class="price">￥ {{ item.price }}</div>
            <div v-if="item.user !== undefined" class="user">
              <span>发布人:</span>
              <span>{{ item.user }}</span>
            </div>
            <div class="other">
              <span>发布时间:</span>
              <span>{{ dayjs(item.addtime).format('YYYY-MM-DD') }}</span>
            </div>
            <div v-if="item.thumbsupnum !== undefined" class="other">
              <span>点赞:</span>
              <span>{{ item.thumbsupnum }}</span>
            </div>
            <div v-if="item.storeupnum !== undefined" class="other">
              <span>收藏:</span>
              <span>{{ item.storeupnum }}</span>
            </div>
            <div v-if="item.clicknum !== undefined" class="other">
              <span>查看:</span>
              <span>{{ item.clicknum }}</span>
            </div>
            <div v-if="item.browseduration !== undefined" class="other">
              <span>时长:</span>
              <span>{{ item.browseduration }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style>
.list-default {
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
    height: auto;
    padding: 20px 0;
    margin: 0;
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
    align-items: flex-start;
    gap: 20px;
    .item {
      flex: 0 0 auto;
      width: calc(25% - 15px);
      height: auto;
      padding: 10px;
      border: 1px solid #eee;
      position: relative;
      cursor: pointer;
      transition: background-color 0.3s linear;
      color: #666;
      &:hover {
        background-color: var(--btn-bg-color-);
        color: var(--btn-font-color-);
        .cover {
          transform: scale(1.05);
        }
        .price {
          color: #8f0e2d;
        }
        .text {
          color: #fff;
        }
      }
    }
  }
  .img-box {
    width: 100%;
    height: 160px;
    border-radius: 10px;
    overflow: hidden;
    position: relative;
    .music {
      position: absolute;
      left: 50%;
      top: 50%;
      transform: translate(-50%, -50%);
      width: 60px;
      height: 60px;
      z-index: 9;
    }
    .cover {
      width: 100%;
      height: 100%;
      object-fit: cover;
      transition: transform 0.3s linear;
    }
  }
  .info-box {
    width: 100%;
    height: auto;
    margin: 0;
    padding: 0;
    .text {
      color: #000000;
      font-size: 18px;
      font-weight: 600;
      margin-bottom: 6px;
    }
    .price {
      color: #e72c59;
      font-size: 16px;
      font-weight: 600;
      margin-bottom: 6px;
    }
    .user,
    .other {
      color: inherit;
      font-size: 12px;
      margin: 0 0 6px;
      display: flex;
      gap: 6px;
    }
  }
}
</style>
