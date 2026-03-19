
<script setup>
/**
 * @description 商品属性
 */
import { inject } from 'vue'

let { detailColumns, data } = inject('detail')
</script>
<template>
  <div class="detail-list">
    <div class="item" v-for="column in detailColumns" :key="column.columnName">
      <component
        :key="column.columnName"
        :is="column.detail_type"
        :column="column"
        :data="data"
        :value="data[column.columnName]"
        :label="column.comments"
      />
    </div>
  </div>
</template>
<style>
/* 商品详情 */
.detail-list {
  margin-top: 20px;
  background: #fff;
  display: flex;
  flex-direction: column;
  gap: 6px;

    padding: 20px;
    border-radius: 10px;

  /* 盒子 */
  .item {
    border: 1px solid #e4e7ed;
    padding: 5px 10px;
  }

  /* 文本 */
  .label {
    color: #999;
    margin-right: 20px;
    font-size: 14px;
  }

  /* 标题 */
  .detail-title {
    display: flex;
    justify-content: space-between;

    .title {
      font-weight: 600;
    }

    .storeup {
      color: #409eff;
      cursor: pointer;
    }
  }

  /* 价格 */
  .detail-price {
    .price {
      color: #f56c6c;
      font-weight: 600;
      font-size: 18px;
    }
  }

  /* 倒计时 */
  .detail-reversetime {
    display: flex;
  }
}
</style>

  