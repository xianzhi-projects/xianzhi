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
  <el-aside>
    <a href="javascript:void(0)">
      <div class="layout-sider-logo">
        <img alt="XianZhi Admin" src="@/assets/logo.png"  />
        <span >XianZhi Admin</span>
      </div>
    </a>
    <el-menu
      :router="true"
      class="el-menu-vertical-demo"
    >
      <MenuTree v-model:menu-list="routerStore.routerList"/>
    </el-menu>
  </el-aside>
</template>

<style lang="less" scoped>
.el-aside {
  user-select: none;
  width: 220px;
  min-height: 100vh;
  a{
    text-decoration: none;
  }
  .layout-sider-logo {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    height: 38px;
    text-align: center;
    font-size: 15px;
    line-height: 38px;
    padding: 5px;
    img {
      margin-left: 10px;
      width: 28px;
      height: 28px;
    }

    span {
      font-weight: 600;
      margin-left: 8px;      // 与图片间距
      vertical-align: middle; // 文字垂直居中
    }
  }

}

</style>
