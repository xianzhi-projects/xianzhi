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

<script setup lang="ts">

</script>

<template>
  <div class="analytics">
    <el-row :gutter="16" style="margin-left: 0; margin-right: 0">
      <el-col :span="6">
        <el-card class="box-card">
          <div class="item">用户量</div>
          <div class="view">
            <el-text>今日活跃用户量</el-text>
            <el-text>11</el-text>
          </div>
          <div class="total">
            <el-text>用户总量</el-text>
            <el-text>20000</el-text>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card">
          <div class="text item">迭代量</div>
          <div class="view">
            <el-text>今日新增迭代数</el-text>
            <el-text>22</el-text>
          </div>
          <div class="total">
            <el-text>迭代总数</el-text>
            <el-text>2000</el-text>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card">
          <div class="text item">合并请求</div>
          <div class="view">
            <el-text>今日新增合并请求</el-text>
            <el-text>20000</el-text>
          </div>
          <div class="total">
            <el-text>合并请求总量</el-text>
            <el-text>2000</el-text>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card">
          <div class="text item">迭代数量</div>
          <div class="view">
            <el-text>今日新增迭代量</el-text>
            <el-text>20000</el-text>
          </div>
          <div class="total">
            <el-text>迭代总量</el-text>
            <el-text>2000</el-text>

          </div>
        </el-card>
      </el-col>

    </el-row>
  </div>
  <div class="echarts">
    <el-row :gutter="16" style="margin-left: 0; margin-right: 0">
      <el-col :span="24">
        <el-card class="box-card">
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<style lang="scss" scoped>
.analytics{
  .el-col {
    .box-card {
      height: 200px;
      border-radius: 10px;
      text-align: left;
      .item {
        font-size: 20px;
        line-height: 20px;
        font-weight: bold;
      }
      .view{
        display: flex;
        justify-content: space-between;
      }
      .total{
        display: flex;
        justify-content: space-between;
      }
    }
  }
}
.echarts {
  height: 350px;
  border-radius: 10px;
  text-align: left;
  margin-top: 10px;
  .box-card{
    height: 350px;
  }
}



</style>
