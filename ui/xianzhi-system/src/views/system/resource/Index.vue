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

import ResourceTree from "@/views/system/resource/ResourceTree.vue";
import ResourceEdit from "@/views/system/resource/ResourceEdit.vue";
</script>

<template>
  <el-row :gutter="16" style="margin-left: 0; margin-right: 0">
    <el-col :span="6">
      <ResourceTree/>
    </el-col>
    <el-col :span="10">
      <ResourceEdit/>
    </el-col>
    <el-col :span="8">
      <ResourceTree/>
    </el-col>
  </el-row>
</template>

<style scoped>

</style>
