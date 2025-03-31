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
import {
  append,
  dataSource,
  defaultNodeKey,
  onSelect,
  refreshResourceTree,
  remove
} from "@/views/system/resource/index.ts";
import {onBeforeMount} from "vue";
import {ResourceType} from "@/types/resource.ts";

onBeforeMount(() => {
  refreshResourceTree()
})





</script>

<template>
  <el-card class="box-card" header="资源列表">
    <template #header>
      <div class="card-header">
        <span>资源列表</span>
        <el-button size="small" type="primary" @click="append(null)">添加资源</el-button>
      </div>
    </template>
    <div class="">
      <el-tree
        :data="dataSource"
        node-key="id"
        default-expand-all
        :current-node-key="defaultNodeKey"
        @node-click="onSelect"
      >
        <template #default="{ node, data }">
          <el-icon  v-if="data.menuIcon && typeof data.menuIcon === 'string'" style="margin-right: 8px">
            <component :is="data.menuIcon"></component>
          </el-icon>
          <div class="custom-tree-node">
            <span style="user-select: none">{{ data.resourceName }}</span>
            <div>
              <el-button v-if="data.resourceType == ResourceType.CATALOG" link  type="primary" @click.stop="append(data)">
                新增
              </el-button>
              <el-popconfirm
                :cancel-button-text="'取消'"
                :confirm-button-text="'删除'"
                :onConfirm="() => remove(data)"
                class="box-item"
                placement="bottom-end"
                title="确认删除吗？"
              >
                <template #reference>
                  <el-button
                    link
                    style="margin-left: 4px"
                    type="danger"
                  >
                    删除
                  </el-button>
                </template>
              </el-popconfirm>


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
  .card-header{
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
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
