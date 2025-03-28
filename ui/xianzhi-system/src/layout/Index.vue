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
  <div class="common-layout">
    <el-container>
      <Aside/>
      <el-container>
        <el-header>
          <Header/>
        </el-header>
        <el-main>Main</el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script lang="ts" setup>
import Aside from "@/layout/components/Aside.vue";
import Header from "@/layout/components/Header.vue";
</script>
<style lang="less" scoped>
.el-header{
  background-color: white;
  color: #333;
  line-height: 60px;
  text-align: right;
  padding: 0 20px;
  box-shadow: 0 2px 5px #f0f1f2;
}
.el-main{
  padding: 20px;
  //background-color: #2c3e50;
}
</style>
