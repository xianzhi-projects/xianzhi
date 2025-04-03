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
import ResourceTree from '@/views/system/resource/components/ResourceTree.vue'
import ResourceEdit from '@/views/system/resource/components/ResourceEdit.vue'
import TreeComponent from "@/components/TreeComponent.vue";
</script>
<template>
  <a-row :gutter="16" style="margin-left: 0; margin-right: 0">
    <a-col :span="6">
      <ResourceTree/>
    </a-col>
    <a-col :span="10">
      <ResourceEdit/>
    </a-col>
    <a-col :span="8">
      <a-card style="width: 100%;height: 1000px" title="">
        <TreeComponent/>
      </a-card>
    </a-col>
  </a-row>
</template>

<style scoped>

</style>
