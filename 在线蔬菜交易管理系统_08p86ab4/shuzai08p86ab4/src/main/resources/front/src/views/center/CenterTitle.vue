<script setup>
/**
 * @description 标题 公共的
 * 很多子页面都会用这个标题组件:
 * title 标题
 */
let { title } = defineProps(['title'])
</script>

<template>
  <div class="centertitle">
    <div class="title">{{ title }}</div>
  </div>
</template>
<style>
.centertitle {
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: url(http://codegen.caihongy.cn/20250926/d00376e8b9924f7896550835a53142b6.png) no-repeat bottom ;

  .title {
    font-weight: 600;
    font-size: 30px;
    position: relative;

  }
}
</style>
