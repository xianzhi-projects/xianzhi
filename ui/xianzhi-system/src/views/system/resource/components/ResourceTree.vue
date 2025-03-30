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
import {ElButton} from "element-plus";
import {append, dataSource, onSelect, refreshResourceTree} from "@/views/system/resource/index.ts";
import {onBeforeMount} from "vue";
import {remove} from "nprogress";

onBeforeMount(() => {
  refreshResourceTree()
})





</script>

<template>
  <el-card class="box-card" header="资源列表">
    <div class="">
      <el-tree
        :data="dataSource"
        node-key="id"
        :expand-on-click-node="false"
        default-expand-all
        @node-click="onSelect"
      >
        <template #default="{ node, data }">
          <el-icon style="margin-right: 8px">
            <component :is="data.menuIcon"></component>
          </el-icon>
          <div class="custom-tree-node">
            <span>{{ data.resourceName }}</span>
            <div>
              <el-button link type="primary" @click="append(data)">
                新增
              </el-button>
              <el-button
                link
                style="margin-left: 4px"
                type="danger"
                @click="remove(node, data)"
              >
                删除
              </el-button>
            </div>
          </div>
        </template>
      </el-tree>
    </div>

  </el-card>

</template>

<style scoped>
.box-card{
  min-height: 800px;
}
.custom-tree-node {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}
</style>
