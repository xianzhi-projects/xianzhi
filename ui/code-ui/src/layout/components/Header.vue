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

import {selectedKeys} from "@/layout/index.ts";
</script>

<template>
  <a-layout-header>
    <div class="logo" />
    <a-menu
      v-model:selectedKeys="selectedKeys"
      :style="{ lineHeight: '64px' }"
      mode="horizontal"
    >
      <a-menu-item key="1">nav 222</a-menu-item>
      <a-menu-item key="2">nav 2</a-menu-item>
      <a-menu-item key="3">nav 3</a-menu-item>
    </a-menu>
  </a-layout-header>
</template>

<style lang="less" scoped>
.ant-layout-header {
  background-color: transparent !important;
}
</style>
