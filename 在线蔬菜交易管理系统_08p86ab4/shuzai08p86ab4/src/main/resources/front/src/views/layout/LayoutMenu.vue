<script setup>
import { ref, watch, inject } from 'vue'
import { useRoute } from 'vue-router'
/**
 * @description 菜单 (角色菜单也可以加到这里)
 *
 * menus 菜单列表
 * item.label 菜单名称
 * item.url 菜单地址 用于页面高亮和路由跳转
 * item.children 二级菜单
 *
 * clickEvent 菜单点击事件
 *
 * 角色菜单:
 * isLogin  是否已登录
 * roleMenus  角色菜单列表
 * roleMenuEvent  角色菜单点击事件
 *
 */

let { menus, clickEvent, isLogin, roleMenus, roleMenuEvent } = inject('header')

// 激活的菜单项,默认首页
const route = useRoute()
const defaultActive = ref('/home')
watch(
  () => route.path,
  newPath => {
    defaultActive.value = newPath
  },
  { immediate: true }
)
</script>

<template>
  <div class="menu-wrapper">
    <el-scrollbar>
      <el-menu :default-active="defaultActive" mode="horizontal" popper-class="menu-poper" :ellipsis="false">
        <template v-for="item in menus">
          <!-- 含有二级菜单 -->
          <el-sub-menu v-if="item.children" :index="item.url" @click="clickEvent(item)">
            <template #title>{{ item.label }}</template>
            <el-menu-item :key="item.url" :index="item.url" @click="clickEvent(item)">全部</el-menu-item>
            <el-menu-item v-for="i in item.children" :key="i.url" :index="i.url" @click="clickEvent(i)">
              {{ i.label }}
            </el-menu-item>
          </el-sub-menu>

          <!-- 一级菜单 -->
          <el-menu-item v-else :key="item.url" :index="item.url" @click="clickEvent(item)">
            {{ item.label }}
          </el-menu-item>
        </template>
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<style>
.menu-wrapper {
  width: 100%;
  background: var(--btn-bg-color-);
  .el-menu {
    --el-menu-bg-color: var(--btn-bg-color-);
    --el-menu-hover-bg-color: var(--btn-bg-hover-color-);
    --el-menu-active-color: #fff;
    --el-menu-text-color: #fff;
    --el-menu-hover-text-color: #fff;
    --el-menu-base-level-padding: 30px;
  }
.el-menu {
      justify-content: center;
}
  .el-menu--horizontal>.el-sub-menu .el-sub-menu__title {
    font-size: 16px;
}
  .el-menu-item {
    font-size: 16px;
}
  .el-sub-menu {
    position: relative;
  }
  .el-menu-item::before,
  .el-sub-menu::before {
    position: absolute;
    right: 0;
    top: 12px;
    z-index: 1;
    content: '';
    background: url(http://codegen.caihongy.cn/20250926/cb562787fdcb4ad18dc845b0c8988d54.png) no-repeat;
    width: 2px;
    height: 36px;
  }
}
</style>
