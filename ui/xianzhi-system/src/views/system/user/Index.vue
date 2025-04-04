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
import {departmentList, onSelect,} from "@/views/system/user/index.ts";
import UserSearch from "@/views/system/user/UserSearch.vue";
import UserList from "@/views/system/user/UserList.vue";
</script>

<template>
  <div class="container">
    <el-row :gutter="16" style="margin-left: 0; margin-right: 0">
      <el-col :span="3">
        <el-card class="department" header="部门信息">
          <el-tree
            :data="departmentList"
            :default-expand-all="true"
            :expand-on-click-node="false"
            :highlight-current="true"
            :empty-text="'部门信息为空'"
            :props="{ label: 'departmentName', children: 'children' }"
            node-key="id"
            @node-click="onSelect"
          />
        </el-card>
      </el-col>
      <el-col :span="21">
        <UserSearch/>
        <UserList/>
      </el-col>
    </el-row>
  </div>
</template>

<style lang="less" scoped>
.container {
  height: calc(-88px + 90vh);
  .el-row {
    height: 100%;
    .department {
      height: 100%;
    }
  }
}

</style>
