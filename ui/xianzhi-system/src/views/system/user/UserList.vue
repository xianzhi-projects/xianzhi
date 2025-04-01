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

<script lang="ts" setup></script>

<template>
  <div class="content">


  </div>

</template>

<style lang="less" scoped>
.content {
  display: flex;
  justify-content: space-between;
  min-height: calc(-88px + 90vh);
  .department {
    width: 325px;
    .el-card {
      height: 100%;
    }
  }
  .user {
    width: 100%;
    margin-left: 10px;
    .user-search{
      .el-form{
        display: flex;
        flex: 1;
        flex-wrap: wrap;
        justify-content: space-between;
      }
    }
    .user-list{
      .el-card{
        width: 100%;
        .el-form{
          .el-form-item{
            width: 600px;
          }
          width: 100%;
        }
      }

    }
  }
}

</style>
