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
  <div class="user-search">
    <el-card>
      <el-form label-position="right">
        <el-form-item label="用户名">
          <el-input/>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input/>
        </el-form-item>
        <el-form-item label="真实姓名">
          <el-input/>
        </el-form-item>
        <el-form-item label="上级菜单">
          <el-input/>
        </el-form-item>
        <el-form-item label="上级菜单">
          <el-input/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary">查询</el-button>
          <el-button >重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
.user-search {
  .el-card {
    .el-form {
      display: flex;
      justify-content: flex-start;
      flex-wrap: wrap;
      gap: 10px;
    }
  }

  margin-bottom: 10px;
}

</style>
