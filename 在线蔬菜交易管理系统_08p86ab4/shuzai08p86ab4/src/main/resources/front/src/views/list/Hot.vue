<script setup>
/**
 * @description 热门信息
 *
 * data.title 标题
 * data.subtitle 副标题
 * data.tableName 表名
 * data.list 单个列表的数据
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
  <div class="hot-default">
    <div class="title">{{ data.comments }}</div>

    <div class="content">
      <div class="item" v-for="item in data.list" :key="item.id" @click="detailEvent(data.tableName, item.id)">
        <div class="img-box">
          <img :src="item.img" />
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
        </div>
      </div>
    </div>
  </div>
</template>
<style>
.hot-default {
  width: 100%;
  padding: 7px 15px;
  background: #fff;

  .title {
    border-bottom: 1px solid #eee;
    padding-bottom: 10px;
    font-size: 18px;
  }

  .content {
    width: 100%;
    height: auto;
    padding: 20px 0;
    margin: 0;
    display: flex;
    flex-direction: column;
    gap: 20px;
    .item {
      display: flex;
      gap: 10px;
      width: 100%;
      height: auto;
      position: relative;
      cursor: pointer;
      &:hover {
        .text {
          color: var(--btn-bg-color-);
        }
      }
    }
  }
  .img-box {
    flex: none;
    width: 100px;
    overflow: hidden;
    img {
      width: 100%;
      height: 100%;
      object-fit: cover;
      transition: transform 0.3s linear;
      &:hover {
        transform: scale(1.08);
      }
    }
  }
  .info-box {
    flex: auto;
    width: 0;

    .text {
      color: #333;
      font-size: 14px;
      transition: color 0.3s linear;
    }
    .price {
      color: #e72c59;
      font-size: 16px;
      font-weight: 600;
      margin: 10px 0;
    }
    .user,
    .other {
      color: #787878;
      font-size: 12px;
      margin: 0 0 6px;
      display: flex;
      gap: 6px;
    }
  }
}
</style>
