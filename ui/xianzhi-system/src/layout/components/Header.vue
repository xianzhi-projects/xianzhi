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
import {logout, userInfo} from "../index.ts";

const url = 'https://img2.baidu.com/it/u=886042428,4063273124&fm=253&fmt=auto&app=138&f=JPEG?w=400&h=400'
</script>

<template>
  <div class="toolbar">
    <span>{{ userInfo.nickName}}</span>
    <el-dropdown >
      <div class="avatar">
        <el-avatar :size="40" :src="url"/>
      </div>
      <div class="settings">
        <el-icon>
          <setting/>
        </el-icon>
      </div>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item>View</el-dropdown-item>
          <el-dropdown-item>Add</el-dropdown-item>
          <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
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
    .avatar{
      margin-left: 20px;
      margin-top: 1px
    }
    .el-icon {
      margin-right: 228px;
      margin-top: 1px
    }
  }
}
</style>
