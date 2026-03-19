
<script setup>
/**
 * @description 个人信息
 *
 * infoList 数据列表
 * item.label 名称
 * item.value 值
 *
 * userAvatar 头像
 */
import { inject } from 'vue'

let { infoList, userAvatar } = inject('center')
</script>

<template>
  <div class="centerinfo">
    <div class="subtitle">我的信息</div>

    <div class="centerinfo-inner">
      <!-- 头像 -->
      <div className="avatar">
        <img className="img" :src="userAvatar" draggable="false" />
      </div>

      <!-- 其它信息 -->
      <div class="info-list">
        <div v-for="(item, index) in infoList" :key="index" class="info-item">
          <span class="label">{{ item.label }}:</span>
          <span class="value">{{ item.value }}</span>
        </div>
      </div>
    </div>
  </div>
</template>
<style>
.centerinfo-inner {
  padding: 20px;
  background-color: #fff;
  border-radius: 8px;
  border: 1px solid #eaeaea;
  .avatar {
    text-align: center;
    margin-bottom: 20px;

    .img {
      width: 100px;
      height: 100px;
      border-radius: 50%;
      object-fit: cover;
    }
  }

  .info-list {
    display: flex;
    flex-wrap: wrap;
    .info-item {
      width: 33.33%;
      margin-bottom: 10px;

      .label {
        color: #999;
      }

      .value {
        margin-left: 10px;
      }
    }
  }
}
</style>
  