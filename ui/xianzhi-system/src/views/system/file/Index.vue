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

import {dialogFormVisible, loading, userList} from "@/views/system/user/index.ts";
</script>

<template>
  <div class="h-100 cw">
    <el-row :gutter="16" >
      <el-col :span="24">
        <el-card class="mb-10">
          <div class="card-header">
            <span>角色列表</span>
            <el-button size="small" type="primary" @click="dialogFormVisible = true">添加角色</el-button>
          </div>
        </el-card>
      </el-col>
      <el-col :span="24">
        <div class="user-list h-100">
          <el-card class="h-100" header="用户管理">
            <template #header>
              <div class="card-header">
                <span>用户列表</span>
                <el-button size="small" type="primary" @click="dialogFormVisible = true">添加用户</el-button>
              </div>
            </template>
            <div class="table-container">
              <el-table v-loading="loading" :data="userList" :empty-text="'暂无数据'" style="width: 100%;">
                <el-table-column label="头像" prop="avatar">
                  <template #default="scope">
                    <div style="display: flex; align-items: center">
                      <el-avatar :size="40" :src="scope.row.avatar" />
                    </div>
                  </template>
                </el-table-column>
                <el-table-column label="用户名" prop="username" />
                <el-table-column label="昵称" prop="nickName" />
                <el-table-column label="真实姓名" prop="realName" />
                <el-table-column label="邮箱地址" prop="email" />
                <el-table-column label="手机号码" prop="phone" />
                <el-table-column label="所属部门" prop="department.departmentName" />
                <el-table-column fixed="right" label="操作">
                  <template #default>
                    <el-button link size="small" type="primary">Edit</el-button>
                  </template>
                </el-table-column>
              </el-table>
              <!-- 添加分页组件 -->
              <!--              <div class="pagination-container">-->
              <!--                <el-pagination-->
              <!--                  v-model:current-page="userPage.pageNo"-->
              <!--                  v-model:page-size="userPage.pageSize"-->
              <!--                  background-->
              <!--                  :page-sizes="[10, 20, 50, 100]"-->
              <!--                  :total="total"-->
              <!--                  layout="total, sizes, prev, pager, next, jumper"-->
              <!--                  @size-change="handleSizeChange"-->
              <!--                  @current-change="handleCurrentChange"-->
              <!--                />-->
              <!--              </div>-->
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
  </div>

</template>

<style  lang="scss" scoped>
.card-header {
  display: flex;
  justify-content: space-between;
}
.user-list {
  .card-header {
    display: flex;
    justify-content: space-between;
  }

  :deep(.el-card) {
    display: flex;
    flex-direction: column;
    height: 100%;
  }

  :deep(.el-card__header) {
    flex-shrink: 0;
  }

  :deep(.el-card__body) {
    flex: 1;
    display: flex;
    flex-direction: column;
    padding: 0;
  }

  .table-container {
    flex: 1;
    overflow: auto;
    display: flex;
    flex-direction: column;
  }

  .el-table {
    width: 100%;
    flex: 1;
  }

  .pagination-container {
    padding: 10px;
    text-align: right;
    flex-shrink: 0;
  }
}


</style>
