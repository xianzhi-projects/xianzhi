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
import {ElButton} from "element-plus";
import {itemDialogFormVisible} from "@/views/system/i18n/index.ts";
import I18nEdit from "@/views/system/i18n/I18nEdit.vue";
import {loading, total, userList, userPage} from "@/views/system/user/index.ts";
</script>

<template>
  <el-card class="box-card cw">
    <template #header>
      <div class="card-header">
        <el-form label-position="right">
          <el-form-item label="用户名">
            <el-input/>
          </el-form-item>
          <el-form-item label="昵称">
            <el-input/>
          </el-form-item>
          <el-form-item>
            <el-button type="primary">查询</el-button>
            <el-button >重置</el-button>
          </el-form-item>
        </el-form>
        <el-button type="primary" @click="itemDialogFormVisible = true">添加资源</el-button>
      </div>
    </template>
    <div class="table-container">
      <el-table v-loading="loading" :data="userList" :empty-text="'暂无数据'" style="width: 100%;" >
        <el-table-column label="头像" prop="avatar">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <el-avatar :size="40" :src="scope.row.avatar" />
            </div>
          </template>
        </el-table-column>
        <el-table-column label="用户名" prop="username" />
        <el-table-column label="昵称" prop="nickName" />
        <el-table-column label="工号" prop="workNumber" />
        <el-table-column label="真实姓名" prop="realName" />
        <el-table-column fixed="right" label="操作">
          <template #default>
            <el-button link size="small" type="primary">Edit</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 添加分页组件 -->
      <div class="pagination-container">
        <el-pagination
          v-model:current-page="userPage.pageNo"
          v-model:page-size="userPage.pageSize"
          :page-sizes="[10, 20, 50, 100]"
          :total="total"
          background
          layout="total, sizes, prev, pager, next, jumper"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>

    </div>
  </el-card>
  <I18nEdit/>
</template>

<style lang="scss" scoped>
.card-header{
  display: flex;
  justify-content: space-between;
  align-items: center;
  .el-form{
    display: flex;
    justify-content: flex-start;
    .el-form-item{
      margin-left: 10px;
    }
  }
}

</style>
