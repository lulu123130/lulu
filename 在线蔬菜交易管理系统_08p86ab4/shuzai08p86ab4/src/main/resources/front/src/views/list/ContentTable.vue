<script setup>
/**
 * @description 表格
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
import '@/components/TableItem/index'
import { getColums } from '@/utils/form'
import { inject } from 'vue'
let { data, hideHeader } = defineProps(['data', 'hideHeader'])
let { detailEvent, moreEvent } = inject('start')
// 所有列
let columns = getColums(data.tableName, 'list')
// 操作按钮
let tableButtons = [
  {
    title: '查看',
    name: '查看',
    key: 'view',
    iconName: 'View',
  },
]
</script>
<template>
  <div class="listfront-default">
    <el-table :data="data.sourceList" row-key="id" :border="false" :show-overflow-tooltip="true">
      <!-- <el-table-column type="selection" :width="100" /> -->

      <el-table-column
        v-for="(column, index) in columns"
        min-width="150"
        :resizable="false"
        :prop="column.columnName"
        :label="column.comments"
        :key="column.columnName"
      >
        <template #default="scope">
          <component
            :is="column.table_type"
            :tableName="data.tableName"
            :row="scope.row"
            :column="column"
            :value="scope.row[column.columnName]"
          />
        </template>
      </el-table-column>

      <!-- 操作 -->
      <el-table-column label="操作" fixed="right" min-width="100" v-if="tableButtons.length">
        <template #default="{ row }">
          <div class="table-button-wrapper">
            <el-button
              v-for="item in tableButtons"
              :key="item.key"
              :class="item.className"
              :icon="item.iconName"
              @click="detailEvent(data.tableName, row.id)"
              size="small"
            >
              {{ item.title }}
            </el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
