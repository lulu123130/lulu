<script setup></script>
<template>
  <el-button class="login-btn" native-type="submit" type="primary">登录</el-button>
</template>
<style>
.login-btn {
  --btn-bg-color-deep: rgb(from var(--btn-bg-color-) calc(r * 0.8) calc(g * 0.8) calc(b * 0.8) / 1);
  border: 0px solid transparent;
  border-radius: 45px;
  padding: 4px 15px;
  margin-bottom: 20px;
  width: 100%;
  font-size: 24px;
  height: 45px;

  background: linear-gradient(
    145deg,
    rgb(from var(--btn-bg-color-) calc(r * 1.2) calc(g * 1.2) calc(b * 1.2) / 1) 0%,
    var(--btn-bg-color-) 50%,
    rgb(from var(--btn-bg-color-) calc(r * 0.9) calc(g * 0.9) calc(b * 0.9) / 1) 100%
  );
  box-shadow: 0 8px 0 var(--btn-bg-color-deep), 0 12px 20px rgba(0, 0, 0, 0.3);
  border-bottom: 3px solid var(--btn-bg-color-deep);

  &:hover {
    border-color: transparent;
    transform: translateY(2px);
    box-shadow: 0 4px 0 var(--btn-bg-color-deep), 0 6px 15px rgb(0 0 0 / 0.3);
  }

  &:active {
    transform: translateY(6px);
    box-shadow: 0 2px 0 var(--btn-bg-color-deep), 0 3px 10px rgb(0 0 0 / 0.2);
  }
}
</style>

