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
 * item.introduction 简介 新闻模式下有 undefined判断
 *
 * hideHeader 隐藏头部
 * detailEvent 点击详情事件
 * moreEvent 更多事件
 *
 */
import { inject } from 'vue'
import dayjs from 'dayjs'

let { data, hideHeader } = defineProps(['data', 'hideHeader'])
let { detailEvent, moreEvent } = inject('start')
</script>

<template>
  <div class="listfront-default">
    <div class="item" v-for="item in data.list" :key="item.id" @click="detailEvent(data.tableName, item.id)">
      <div v-for="title in item.titles" class="goodtitle">{{ title }}</div>

      <div class="item-inner">
        <div class="info-box">
          <div class="info-box-inner">
            <div v-if="item.price !== undefined" class="price">￥ {{ item.price }}</div>
            <div v-if="item.introduction !== undefined" class="introduction">{{ item.introduction }}</div>
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

        <div class="img-box" v-if="item.img || data.hasMusic">
          <img
          v-if="data.hasMusic"
          class="music"
          src="http://codegen.caihongy.cn/20251230/096adf8336ff4c9397450b81c24cc10f.png"
          @click.stop="data.musicEvent(item)"
          />
          <img class="cover" :src="item.img" />
        </div>
      </div>
    </div>
  </div>
</template>
<style>
.listfront-default {
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
    width: calc(50% - 10px);
    height: auto;
    padding: 10px;
    border: 1px solid #eee;
    position: relative;
    cursor: pointer;
    box-shadow: inset 0 0 0 5px #e7ebf3;
    background: #fff;

    transition: boxShadow 0.3s linear, transform 0.3s linear;

    &::before {
      box-sizing: inherit;
      content: '';
      position: absolute;
      width: 100%;
      height: 100%;
      top: 0;
      left: 0;

      border-top: 5px solid var(--btn-bg-color-);
      border-bottom: 5px solid var(--btn-bg-color-);
      transform: scale3d(0, 1, 1);
      transform-origin: left;
    }
    &::after {
      box-sizing: inherit;
      content: '';
      position: absolute;
      width: 100%;
      height: 100%;
      top: 0;
      left: 0;

      border-left: 5px solid var(--btn-bg-color-);
      border-right: 5px solid var(--btn-bg-color-);
      transform: scale3d(1, 0, 1);
      transform-origin: bottom;
    }

    &:hover::before,
    &:hover::after {
      transform: scale3d(1, 1, 1);
      transition: transform 0.4s;
    }

    &:hover .goodtitle {
      color: var(--btn-bg-color-);
    }
    &:hover .cover {
      transform: scale(1.08);
      transition: transform 0.3s linear;
    }

    .goodtitle {
      color: #000000;
      font-size: 16px;
      line-height: 32px;
      font-weight: 600;
    }
  }
  .item-inner {
    display: flex;
    gap: 10px;
  }

  .img-box {
    flex: auto;
    width: 50%;
    height: 140px;
    overflow: hidden;
    border-radius: 10px;
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
    }
  }
  .info-box {
    flex: auto;
    width: 50%;
    .price {
      color: #e72c59;
      font-size: 16px;
      font-weight: 600;
    }
    .introduction {
      color: #787878;
      font-size: 14px;
      display: -webkit-box;
      -webkit-box-orient: vertical;
      -webkit-line-clamp: 3;
      overflow: hidden;
      text-overflow: ellipsis;
      margin-bottom: 6px;
    }
    .user,
    .other {
      color: #787878;
      font-size: 12px;
      display: flex;
      gap: 6px;
    }
  }
  .info-box-inner {
    display: flex;
    flex-direction: column;
    gap: 6px;
  }
}
</style>
