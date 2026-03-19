
<script setup>
/**
 * @description 标题logo
 *
 * projectName 项目名
 * $projectImages.logo logo图片
 */
</script>
<template>
  <div class="login-title">
    <div class="logo" v-if="$projectImages.logo">
      <img :src="$projectImages.logo" draggable="false" />
    </div>

    <div class="title">{{ projectName }}</div>
  </div>
</template>
<style>
.login-title {
  padding: 0;
  margin: 0 0 30px 0;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;

  .logo {
    height: 60px;
    img {
      width: 100%;
      height: 100%;
      object-fit: cover;
    }
  }

  .title {
    color: #444444;
    font-weight: bold;
    font-size: 28px;
  }
}
</style>
  
