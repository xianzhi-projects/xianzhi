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

<script setup>
import {ResourceType} from "@/types/system/resource.js";

const menuList = defineModel('menuList')
</script>


<template>


  <div>
    <template v-for="item in menuList" :key="item.path">
      <!--      分为两种方式渲染：有子菜单和没有子菜单-->
      <el-sub-menu v-if="item.children && item.meta.resourceType === ResourceType.CATALOG"
                   :index="item.path">
        <template #title>

          <el-icon v-if="item.meta.icon && typeof item.meta.icon === 'string'">
            <component :is="item.meta.icon"></component>
          </el-icon>
          <span>{{ item.name }}</span>
        </template>
        <!--        有子菜单的继续遍历（递归）-->
        <MenuTree :menuList="item.children"></MenuTree>
      </el-sub-menu>
      <!--      没有子菜单-->
      <el-menu-item v-if="item.meta.resourceType === ResourceType.MENU" :index="item.path">
        <el-icon v-if="item.meta.icon && typeof item.meta.icon === 'string'">
          <component :is="item.meta.icon"></component>
        </el-icon>
        <span>{{ item.name }}</span>
      </el-menu-item>
    </template>
  </div>


</template>


<style lang="less" scoped>
.el-menu-item{
  padding: 0;
  color: #333;
  width: 95%;
  margin: 0 auto;
  height: 50px;
}
.el-sub-menu{
  padding: 0;
  color: #333;
  width: 95%;
  margin: 0 auto;
}
.el-menu-item.is-active{
  background-color: #d7e7f9;
  color: #333;
  border-radius: 10px;
  font-weight: bold;
}


</style>
