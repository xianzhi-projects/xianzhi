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

<template>
  <a-card style="width: 100%;height: 1000px" title="">
    <template #extra>
      <a-button type="primary">新增菜单</a-button>
    </template>
    <TreeComponent/>
  </a-card>
</template>

<script lang="ts" setup>
import TreeComponent from "@/components/TreeComponent.vue";
import {onMounted} from "vue";

onMounted(() => {
  initTreeData()
  // 默认展开第一层
  autoExpandParent.value = false
})
</script>


<style scoped></style>
