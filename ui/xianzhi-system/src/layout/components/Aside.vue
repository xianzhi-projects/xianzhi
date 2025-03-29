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
import {onBeforeMount} from "vue";
import {menus, refreshMenu} from "@/layout/index.ts";
import {useRouterStore} from "@/stores/routerStore.ts";
import router from "@/router";
import MenuTree from "@/layout/components/MenuTree.vue";

const routerStore = useRouterStore();
// 页面加载的时候，将路由配置转换为菜单配置
onBeforeMount(() => {
  const routerList = routerStore.routerList;
  if (routerList){
    refreshMenu(routerList)
  }else{
    router.push("/login")
  }
})

</script>

<template>
  <el-aside width="200px">
    <div class="layout-sider-logo">
      <img alt="" height="28" src="@/assets/logo.png"  width="28"/>
      <span >先知后台管理系统</span>
    </div>
    <MenuTree v-model:menu-list="menus"/>
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
