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
import {itemDialogFormVisible} from "@/views/system/i18n/index.ts";
</script>

<template>
  <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <div>

        </div>
        <el-button size="small" type="primary" @click="itemDialogFormVisible = true">添加资源</el-button>
      </div>
    </template>
    <div class="">
    </div>
    <div class="">
    </div>

  </el-card>

</template>

<style lang="scss" scoped>
.card-header{
  display: flex;
  justify-content: space-between;
  align-items: center;
}

</style>
