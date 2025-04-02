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

import {
  append,
  dataSource,
  defaultNodeKey,
  onSelect,
  remove
} from "@/views/system/resource/index.ts";
import {ResourceType} from "@/types/system/resource.ts";
import {ElButton} from "element-plus";

const props = defineProps<{
  // 是否显示新增按钮
  showCreate: {
    type: Boolean,
    default: false
  },
  // 树数据
  dataSource: {
    type: Array<any>,
    default: []
  },
  // node的唯一标识
  nodeKey: {
    type: string,
    default: 'id'
  },
  // 是否显示复选框
  showCheckbox: {
    type: Boolean,
    default: false
  }
  // 空数据的时候展示文字
  emptyText: {
    type: String,
    default: '暂无数据'
  }
  // 默认选中节点
  defaultNodeKey: {
    type: String,
    default: ''
  }
  // 是否默认展开所有节点
  defaultExpandAll: {
    type: Boolean,
    default: false
  }
}>();


// 定义事件
const emit = defineEmits([
  'node-click',
  'check-change',
  'node-expand',
  'node-collapse',
  'current-change',
  'node-drag-start',
  'node-drag-end',
  'node-drop',
]);





</script>

<template>
  <el-card class="box-card" header="资源列表">
    <template #header>
      <div class="card-header">
        <span>资源列表</span>
        <el-button v-if="props.showCreate" size="small" type="primary" @click="append(null)">Add
        </el-button>
      </div>
    </template>
    <div class="">
      <el-tree
        :current-node-key="defaultNodeKey"
        :data="dataSource"
        :default-expand-all="defaultExpandAll"
        :empty-text="emptyText"
        :node-key="nodeKey"
        @node-click="onSelect"
      >
        <template #default="{ node, data }">
          <el-icon v-if="data.menuIcon && typeof data.menuIcon === 'string'"
                   style="margin-right: 8px">
            <component :is="data.menuIcon"></component>
          </el-icon>
          <div class="custom-tree-node">
            <span style="user-select: none">{{ data.resourceName }}</span>
            <div>
              <el-button v-if="data.resourceType == ResourceType.CATALOG" link type="primary"
                         @click.stop="append(data)">
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

</style>
