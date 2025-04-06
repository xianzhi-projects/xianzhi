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
  <div style="width: 1200px; margin: 0 auto;display: flex;justify-content: space-between;">
    <div >
      <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
        <el-tab-pane label="我参与的项目" name="first">User</el-tab-pane>
        <el-tab-pane label="我管理的项目" name="second">Config</el-tab-pane>

      </el-tabs>
    </div>
    <div>
      <el-button>新增项目</el-button>
    </div>

  </div>
</template>

<script lang="ts" setup>
import {ref} from 'vue'
import type {TabsPaneContext} from 'element-plus'

const activeName = ref('first')

const handleClick = (tab: TabsPaneContext, event: Event) => {
  console.log(tab, event)
}
</script>

<style>

</style>
