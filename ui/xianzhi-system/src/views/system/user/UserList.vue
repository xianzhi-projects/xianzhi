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

import {
  dialogFormVisible,
  loading,
  refreshDepartmentList,
  refreshUserList,
  selectedNode,
  userList
} from "@/views/system/user/index.ts";
import {onBeforeMount, ref, watch} from "vue";
import type {UserPage} from "@/types/system/user.ts";
import UserEdit from "@/views/system/user/UserEdit.vue";

const userPage = ref<UserPage>({
  pageNo: 1,
  pageSize: 10,
  departmentId:''

})
onBeforeMount(() => {
  refreshDepartmentList()
  refreshUserList(userPage.value)
})

// 监听选中的节点
watch(() => selectedNode.value, (newVal) => {
    console.log('选中的节点', newVal)
    userPage.value.departmentId = newVal?.id
    refreshUserList(userPage.value)
  },
)
</script>

<template>
  <div class="user-list">
    <el-card header="用户管理">
      <template #header>
        <div class="card-header">
          <span>用户列表</span>
          <el-button size="small" type="primary" @click="dialogFormVisible = true">添加用户</el-button>
        </div>
      </template>
      <el-table v-loading="loading" :data="userList" :empty-text="'暂无数据'" style="width: 100%;height: 100%">
        <el-table-column label="头像" prop="avatar">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <el-avatar :size="40" :src="scope.row.avatar" />
            </div>
          </template>
        </el-table-column>
        <el-table-column label="用户名" prop="username"/>
        <el-table-column label="昵称" prop="nickName"/>
        <el-table-column label="真实姓名" prop="realName"/>
        <el-table-column label="邮箱地址" prop="email"/>
        <el-table-column label="手机号码" prop="phone"/>
        <el-table-column label="所属部门" prop="department.departmentName"/>
        <el-table-column fixed="right" label="操作">
          <template #default>
            <el-button link size="small" type="primary">Edit</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
  <UserEdit/>
</template>

<style lang="scss" scoped>
.user-list{
  height: 100%;
  .el-card{
    height: 100%;
    .card-header{
      display: flex;
      justify-content: space-between;
    }
  }

}

</style>
