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
import {useRouterStore} from "@/stores/routerStore.ts";
import MenuTree from "@/layout/components/MenuTree.vue";

const routerStore = useRouterStore();
// 页面加载的时候，将路由配置转换为菜单配置

</script>

<template>
  <el-aside width="200px">
    <div class="layout-sider-logo">
      <img alt="" height="28" src="@/assets/logo.png"  width="28"/>
      <span >先知后台管理系统</span>
    </div>
    <el-menu
      :router="true"
      background-color="#333"
      class="el-menu-vertical-demo"
      text-color="#fff"
    >
      <MenuTree v-model:menu-list="routerStore.routerList"/>
    </el-menu>
  </el-aside>
</template>

<style lang="less" scoped>
.el-aside {
  user-select: none;
  width: 250px;
  min-height: 100vh;
  background-color: #333;
  color: #fff;
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
