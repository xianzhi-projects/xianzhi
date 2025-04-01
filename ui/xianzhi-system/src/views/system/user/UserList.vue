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

import {append, userList} from "@/views/system/user/index.ts";
import Pagination from "@/components/pagination/Pagination.vue";
</script>

<template>
  <div class="content">
    <div class="department">
      <el-card header="部门信息">
      </el-card>
    </div>
    <div class="user">
      <div class="user-search">
        <el-card>
          <el-form>
            <el-form-item label="上级菜单">
              <el-input />
            </el-form-item>
            <el-form-item label="上级菜单">
              <el-input/>
            </el-form-item>
            <el-form-item label="上级菜单">
              <el-input />
            </el-form-item>
            <el-form-item label="上级菜单">
              <el-input />
            </el-form-item>
            <el-form-item label="上级菜单">
              <el-input />
            </el-form-item>

          </el-form>
        </el-card>
      </div>
      <div content="user-list">
        <el-card header="用户管理">
          <template #header>
            <div class="card-header">
              <span>用户列表</span>
              <el-button size="small" type="primary" @click="append(null)">添加用户</el-button>
            </div>
          </template>
          <el-table :data="userList" :empty-text="'暂无数据'" style="width: 100%">
            <el-table-column label="头像" prop="date"/>
            <el-table-column label="用户名" prop="name"/>
            <el-table-column label="昵称" prop="state"/>
            <el-table-column label="部门" prop="department"/>
            <el-table-column label="真实姓名" prop="city"/>
            <el-table-column label="邮箱地址" prop="address"/>
            <el-table-column label="手机号码" prop="zip"/>
            <el-table-column label="用户状态" prop="userStatus"/>
            <el-table-column label="创建时间" prop="createAt"/>
            <el-table-column fixed="right" label="操作">
              <template #default>
                <!--            <el-button link type="primary" size="small" @click="handleClick">-->
                <!--              Detail-->
                <!--            </el-button>-->
                <el-button link size="small" type="primary">Edit</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
        <Pagination/>
      </div>
    </div>
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
