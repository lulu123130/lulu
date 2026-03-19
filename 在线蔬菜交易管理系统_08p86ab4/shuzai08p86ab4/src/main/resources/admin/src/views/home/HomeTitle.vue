
<script setup>
/**
 * @description 首页标题
 * projectName 项目名
 * $projectImages.logo logo图片
 */
</script>
<template>
  <div class="home-title">
    <div class="logo" v-if="$projectImages.logo">
      <img :src="$projectImages.logo" draggable="false" />
    </div>

    <div class="title">{{ projectName }}</div>
  </div>
</template>
<style>
.home-title {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;

  .logo {
    width: 90px;
    height: 90px;
    img {
      width: 100%;
      height: 100%;
      object-fit: contain;
    }
  }

  .title {
    color: #000;
    font-weight: bold;
    font-size: 28px;
  }
}
</style> 
  