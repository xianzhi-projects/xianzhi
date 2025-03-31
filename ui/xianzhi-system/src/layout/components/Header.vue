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

<script lang="ts" setup>
import {Setting} from '@element-plus/icons-vue'

const url = 'https://img2.baidu.com/it/u=886042428,4063273124&fm=253&fmt=auto&app=138&f=JPEG?w=400&h=400'
</script>

<template>
  <div class="toolbar">
    <el-avatar :size="40" :src="url"/>
    <el-dropdown>
      <el-icon>
        <setting/>
      </el-icon>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item>View</el-dropdown-item>
          <el-dropdown-item>Add</el-dropdown-item>
          <el-dropdown-item>Delete</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
    <span>Tom</span>
  </div>
</template>

<style lang="less" scoped>

.toolbar {
  user-select: none;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 20px;

  .el-dropdown {
    .el-icon {
      margin-right: 8px;
      margin-top: 1px
    }
  }
}
</style>
