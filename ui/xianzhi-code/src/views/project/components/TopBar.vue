<!--
  -  Copyright 2025 XianZhi Group .
  -
  -  Licensed under the Apache License, Version 2.0 (the "License");
  -  you may not use this file except in compliance with the License.
  -  You may obtain a copy of the License at
  -
  -      http://www.apache.org/licenses/LICENSE-2.0
  -
  -  Unless required by applicable law or agreed to in writing, software
  -  distributed under the License is distributed on an "AS IS" BASIS,
  -  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  -  See the License for the specific language governing permissions and
  -  limitations under the License.
  -->

<!-- src/components/TopBar.vue -->
<template>
  <div class="top-bar">
    <!-- 选项卡，用于切换视图，"待审核"带徽章显示1 -->
    <el-tabs v-model="activeTab">
      <el-tab-pane name="review">
        <template #label>
          <span>待审核 <el-badge :value="1" class="badge" /></span>
        </template>
      </el-tab-pane>
      <el-tab-pane label="待编码" name="coding" />
    </el-tabs>
    <!-- 右侧工具栏，包含搜索、按钮和用户设置 -->
    <div class="toolbar-right">
      <!-- 搜索输入框，带放大镜图标 -->
      <el-input v-model="searchQuery" class="search-input" placeholder="搜索">
        <template #prefix>
          <el-icon><Search /></el-icon>
        </template>
      </el-input>
      <!-- 导入代码库按钮，蓝色风格 -->
      <el-button type="primary" @click="projectEditFlag = true">新增项目</el-button>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {ref} from 'vue';
import {ElBadge, ElButton, ElIcon, ElInput, ElTabPane, ElTabs} from 'element-plus';
import {Search} from '@element-plus/icons-vue';
import {projectEditFlag} from "@/views/project/index.ts";

// 选项卡当前激活项，初始化为"review"
const activeTab = ref('review');
// 搜索输入框的值，当前为占位符
const searchQuery = ref('');
</script>

<style scoped>
.top-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
  height: 60px;
  background-color: #f5f5f5; /* 顶部栏背景，基于描述近似 */
}

.toolbar-right {
  display: flex;
  align-items: center;
}

.search-input {
  width: 200px;
  margin-right: 10px;
}

.el-button + .el-button {
  margin-left: 10px;
}

.el-icon {
  margin-left: 10px;
}
</style>
