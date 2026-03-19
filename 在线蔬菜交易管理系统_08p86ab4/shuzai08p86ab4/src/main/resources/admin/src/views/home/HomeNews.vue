
<script setup>
import { inject, ref } from 'vue'

/**
 * @description 新闻资讯
 * newsData 新闻数据
 * newsData.tilte 新闻资讯 别名
 * newsData.list 新闻列表
 * item.addtime 发布时间
 * item.title 标题
 * item.introduction 简介
 * item.content 内容
 */

let { newsData } = inject('home')

const dialogVisible = ref(false)
const actData = ref({})
function showContent(item) {
  actData.value = item
  dialogVisible.value = true
}
</script>
<template>
  <div class="home-news">
    <div class="title">{{ newsData.title }}</div>
    <div class="list">
      <div v-for="(item, index) in newsData.list" :key="item.id" class="item" @click="showContent(item)">
        <span class="index">0{{ index + 1 }}</span>
        <span class="label">{{ item.title }}</span>
      </div>
    </div>

    <el-dialog v-model="dialogVisible" :title="actData.title" width="80%">
      <div class="introduction">{{ actData.introduction }}</div>
      <div class="ql-snow ql-editor" v-html="actData.content"></div>
    </el-dialog>
  </div>
</template>
<style>
.home-news {
  width: calc(50% - 10px);
  background: #fff;
  border-radius: 10px;
  padding: 10px;

  .title {
    font-weight: 600;
    text-align: center;
    height: 32px;
    line-height: 32px;    
  }
  .item {
    width:100%;
    margin-top: 20px;
    .index {
      color: #bbb;
      font-weight: 700;
      font-size: 20px;
      margin-right: 10px;
    }
    .label {
      color: #000;
      transition: all 0.3s linear;
      cursor: pointer;
    }
    &:hover {
      .label {
        color: #1890ff;
      }
    }

    &:nth-of-type(1) {
      .index {
        color: #ef7900;
      }
    }
    &:nth-of-type(2) {
      .index {
        color: #2775a7;
      }
    }
    &:nth-of-type(3) {
      .index {
        color: #1bb4bb;
      }
    }
    &:nth-of-type(4) {
      .index {
        color: #b997b3;
      }
    }
  }
}
</style>
  
