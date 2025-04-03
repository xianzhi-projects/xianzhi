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
// 无逻辑，仅渲染路由视图
</script>

<template>
  <a-layout-content style="padding: 16px; height: calc(100vh - 64px); overflow: auto;">
    <a-watermark content="先知研发效能平台">
      <div style="height: 100%;">
        <router-view />
      </div>
    </a-watermark>

  </a-layout-content>
</template>

<style scoped>
/* 无需额外样式 */
</style>
