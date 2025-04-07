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

</script>

<template>
  <el-menu
    :router="true"
    background-color="#3b4e8c"
    class="el-menu-vertical-demo"
    default-active="/"
    text-color="#fff"
    @close="handleClose"
    @open="handleOpen"
  >
    <el-menu-item index="/">
      <el-icon><icon-menu /></el-icon>
      <span>代码库</span>
    </el-menu-item>
    <el-menu-item index="/project_group">
      <el-icon><document /></el-icon>
      <span>代码组</span>
    </el-menu-item>
    <el-menu-item index="/pull_request">
      <el-icon><setting /></el-icon>
      <span>合并请求</span>
    </el-menu-item>
    <el-menu-item index="4">
      <el-icon><setting /></el-icon>
      <span>回收站</span>
    </el-menu-item>
  </el-menu>
</template>

<style scoped>

</style>
