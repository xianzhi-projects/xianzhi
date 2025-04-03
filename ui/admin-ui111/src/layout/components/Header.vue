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
import model from '@/layout/index.ts';
import {
  MenuFoldOutlined,
  MenuUnfoldOutlined,
  SearchOutlined,
  SettingOutlined
} from '@ant-design/icons-vue';
</script>

<template>
  <a-layout-header>
    <div class="trigger" @click="() => (model.collapsed.value = !model.collapsed.value)">
      <menu-unfold-outlined v-if="model.collapsed.value" />
      <menu-fold-outlined v-else />
    </div>
    <div class="header-right">
      <search-outlined class="icon" />
      <setting-outlined class="icon" />
      <a-avatar class="avatar">USER</a-avatar>
      <a-space>超级管理员</a-space>
    </div>
  </a-layout-header>
</template>

<style scoped>
.ant-layout-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #fff;
  padding: 0 16px;
  border-bottom: 1px solid #f0f0f0;
  height: 64px;

  .trigger {
    font-size: 18px;
    cursor: pointer;
    transition: color 0.3s;

    &:hover {
      color: #1890ff;
    }
  }

  .header-right {
    display: flex;
    align-items: center;

    .icon {
      font-size: 18px;
      margin-right: 16px;
      cursor: pointer;
      transition: color 0.3s;

      &:hover {
        color: #1890ff;
      }
    }

    .avatar {
      width: 32px;
      height: 32px;
      cursor: pointer;
      margin-right: 10px;
    }
  }
}
</style>
