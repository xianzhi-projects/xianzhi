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
import {ref} from "vue";

const project = ref({});

const createProject = async () => {
  const res = await fetch('/api/project', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(project.value)
  });
  if (res.ok) {
    alert('项目创建成功');
  } else {
    alert('项目创建失败');
  }
};
</script>

<template>
  <div>
    <el-form class="create-project-form" label-width="80px">
      <el-form-item label="项目名称">
        <el-input v-model="projectName" placeholder="请输入项目名称"></el-input>
      </el-form-item>
      <el-form-item label="项目描述">
        <el-input v-model="projectDescription" placeholder="请输入项目描述" type="textarea"></el-input>
      </el-form-item>
      <el-form-item label="项目类型">
        <el-select v-model="projectType" placeholder="请选择项目类型">
          <el-option label="私有" value="private"></el-option>
          <el-option label="公共" value="public"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="创建">
        <el-button type="primary" @click="createProject">创建</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<style scoped>

</style>
