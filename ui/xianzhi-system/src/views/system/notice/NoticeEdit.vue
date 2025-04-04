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
  <el-card class="box-card" header="通知公告">
    <template #header>
      <div class="card-header">
        <span>通知公告</span>
        <el-button size="small" type="primary" @click="append(null)">添加通知公告</el-button>
      </div>
    </template>
    <div class="">
      <el-tree
        :current-node-key="defaultNodeKey"
        :data="dataSource"
        default-expand-all
        node-key="id"
        @node-click="onSelect"
      >
        <template #default="{ node, data }">
          <el-icon  v-if="data.menuIcon && typeof data.menuIcon === 'string'" style="margin-right: 8px">
            <component :is="data.menuIcon"></component>
          </el-icon>
          <div class="custom-tree-node">
            {{ data.name }}
          </div>
        </template>
      </el-tree>
    </div>
  </el-card>
</template>

<style scoped>

</style>
