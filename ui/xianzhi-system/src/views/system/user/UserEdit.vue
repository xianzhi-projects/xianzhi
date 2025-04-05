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

import {departmentList, dialogFormVisible, selectedNode} from "@/views/system/user/index.ts";
import {ref, watch} from "vue";
import type {UserDTO} from "@/types/system/user.ts";

const genderList = ref([
  {label: '男', value: '1'},
  {label: '女', value: '2'},
  {label: '未知', value: '0'}
])
const form = ref<UserDTO>({
  id: '',
  username: '',
  password: '',
  nickName: '',
  realName: '',
  email: '',
  phone: '',
  departmentId: '',
  gender: '',


})

watch(() => selectedNode.value, (newVal) => {
      form.value.departmentId = newVal?.id

    },
)
const onSubmit = () => {
  console.log('submit!')
}
</script>

<template>
  <el-dialog v-model="dialogFormVisible" :close-on-click-modal="false" title="添加用户" width="600">
    <el-form :model="form" label-position="right" label-width="90px" style="max-width: 600px">
      <div style="display: flex;justify-content: space-between;padding: 0 10px">
        <el-form-item :required="true" label="用户名">
          <el-input v-model="form.username" placeholder="请输入用户名"/>
        </el-form-item>
        <el-form-item :required="true" label="密码">
          <el-input v-model="form.password" placeholder="请输入密码" type="password"/>
        </el-form-item>
      </div>
      <div style="display: flex;justify-content: space-between;padding: 0 10px">
        <el-form-item :required="true" label="昵称">
          <el-input v-model="form.nickName"/>
        </el-form-item>
        <el-form-item :required="true" label="姓名">
          <el-input v-model="form.realName"/>
        </el-form-item>
      </div>
      <div style="display: flex;justify-content: space-between;padding: 0 10px">
        <el-form-item :required="true" label="所属部门">
          <el-tree-select
              v-model="form.departmentId"
              :data="departmentList"
              :no-data-text="'暂无数据'"
              :placeholder="'请选择部门'"
              :props="{ label: 'departmentName',value: 'id' }"
              :value-key="'id'"
              check-strictly
              style="width: 195px"
          />
        </el-form-item>
        <el-form-item :required="true" label="用户性别">
          <el-select
              v-model="form.gender"
              :no-data-text="'暂无数据'"
              placeholder="请选择用户性别"
              style="width: 195px">
            <el-option
                v-for="item in genderList"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
      </div>
      <div style="padding: 0 10px">
        <el-form-item label="备注">
          <el-input v-model="form.desc" type="textarea"/>
        </el-form-item>
      </div>

    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="onSubmit">
          添加
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style scoped>

</style>
