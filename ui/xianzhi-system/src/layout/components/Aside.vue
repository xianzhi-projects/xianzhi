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
import {Document, Location, Menu as IconMenu, Setting,} from '@element-plus/icons-vue'
import {ref} from "vue";

const url = ref('@/assets/images/logo.png')
</script>

<template>
  <el-aside width="200px">
    <div class="layout-sider-logo">
      <img alt="" height="28" src="@/assets/logo.png"  width="28"/>
      <span >先知后台管理系统</span>
    </div>
    <el-menu
      background-color="#333"
      class="el-menu-vertical-demo"
      default-active="2"
      text-color="#fff"
    >
      <el-sub-menu index="1">
        <template #title>
          <el-icon>
            <location/>
          </el-icon>
          <span>Navigator One</span>
        </template>
        <el-menu-item-group title="Group One">
          <el-menu-item index="1-1">item one</el-menu-item>
          <el-menu-item index="1-2">item two</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="Group Two">
          <el-menu-item index="1-3">item three</el-menu-item>
        </el-menu-item-group>
        <el-sub-menu index="1-4">
          <template #title>item four</template>
          <el-menu-item index="1-4-1">item one</el-menu-item>
        </el-sub-menu>
      </el-sub-menu>
      <el-menu-item index="2">
        <el-icon>
          <icon-menu/>
        </el-icon>
        <span>Navigator Two</span>
      </el-menu-item>
      <el-menu-item disabled index="3">
        <el-icon>
          <document/>
        </el-icon>
        <span>Navigator Three</span>
      </el-menu-item>
      <el-menu-item index="4">
        <el-icon>
          <setting/>
        </el-icon>
        <span>Navigator Four</span>
      </el-menu-item>
    </el-menu>
  </el-aside>
</template>

<style lang="less" scoped>
.el-aside {
  width: 250px;
  min-height: 100vh;
  background-color: #333;
}
// 侧边栏 Logo 样式
.layout-sider-logo {
  height: 32px;
  margin: 16px;
  text-align: center;
  color: white;
  font-size: 20px;
  line-height: 32px;
  padding: 5px;

  img {
    vertical-align: middle; // 图片垂直居中
  }

  span {
    margin-left: 8px;      // 与图片间距
    vertical-align: middle; // 文字垂直居中
  }
}

</style>
