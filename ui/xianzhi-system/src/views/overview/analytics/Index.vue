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
    <el-row>
      <el-col :span="6">
        <el-card class="box-card" header="数据分析">
          <div class="text item">数据分析</div>
        </el-card>
      </el-col>
      <el-col :span="6" >
        <el-card class="box-card" header="数据分析">
          <div class="text item">数据分析</div>
        </el-card>
      </el-col>
      <el-col :span="6" >
        <el-card class="box-card" header="数据分析">
          <div class="text item">数据分析</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card" header="数据分析">
          <div class="text item">数据分析</div>
        </el-card>
      </el-col>
    </el-row>
  </div>
  <div>
    <el-row>
      <el-col :span="24">
        <el-card class="box-card" header="数据分析">
          <div class="text item">数据分析</div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<style lang="less" scoped>
.analytics{
  .el-col{
    .box-card{
      width: 95%;
      height: 200px;
      border-radius: 10px;
      box-shadow: 0 2px 5px #f0f1f2;
      text-align: left;
      .clearfix{
        font-size: 20px;
        line-height: 60px;
        color: #333;
        font-weight: bold;
      }
      .text{
        font-size: 20px;
        line-height: 200px;
        color: #333;
        font-weight: bold;
      }
      .item{
        font-size: 14px;
        line-height: 30px;
        color: #999;
      }
    }
  }
}


</style>
