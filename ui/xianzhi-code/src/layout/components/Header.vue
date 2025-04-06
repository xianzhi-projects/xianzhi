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
import imageUrl from '@/assets/logo.png';
</script>

<template>
  <div class="wrapper">
    <div style="display: flex; align-items: center;">
      <el-image
        :src="imageUrl"
        fit="contain"
        style="width: 100%; max-width: 64px; margin: 0 auto;"
      />
      <a class="title">先知代码托管平台</a>
    </div>

    <el-menu
      :default-active="$route.path"
      :router="true"
      background-color="transparent"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
    >
      <el-menu-item index="/">项目</el-menu-item>
      <el-menu-item index="/project_group">分组</el-menu-item>
      <el-menu-item index="/pull_request">合并请求</el-menu-item>
    </el-menu>
  </div>
</template>

<style lang="scss" scoped>
.wrapper{
  display: flex;
  justify-content: flex-start;
  .title{
    margin-left: 30px;
  }
  .el-menu{
    width: 500px;
    margin-left: 50px;
  }
}

</style>
