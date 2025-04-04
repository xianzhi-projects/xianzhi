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

import {dialogFormVisible} from "@/views/system/user/index.ts";
import {ref} from "vue";
import type {UserDTO} from "@/types/system/user.ts";

const form = ref<UserDTO>({
  id: '',
  username: '',
  password: '',
  nickName: '',
  realName: '',
  email: '',
  phone: '',
  departmentId: '',


})
const onSubmit = () => {
  console.log('submit!')
}
</script>

<template>
  <el-dialog v-model="dialogFormVisible" title="添加用户" width="500">
    <el-form :model="form" label-width="auto" style="max-width: 600px">
      <div style="display: flex; gap: 3px">
        <el-form-item label="用户名">
            <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.name" />
        </el-form-item>
      </div>
      <el-form-item label="昵称">
        <el-input v-model="form.name" />

      </el-form-item>
      <el-form-item label="Instant delivery">
        <el-switch v-model="form.delivery" />
      </el-form-item>
      <el-form-item label="Activity type">
        <el-checkbox-group v-model="form.type">
          <el-checkbox name="type" value="Online activities">
            Online activities
          </el-checkbox>
          <el-checkbox name="type" value="Promotion activities">
            Promotion activities
          </el-checkbox>
          <el-checkbox name="type" value="Offline activities">
            Offline activities
          </el-checkbox>
          <el-checkbox name="type" value="Simple brand exposure">
            Simple brand exposure
          </el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="Resources">
        <el-radio-group v-model="form.resource">
          <el-radio value="Sponsor">Sponsor</el-radio>
          <el-radio value="Venue">Venue</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="Activity form">
        <el-input v-model="form.desc" type="textarea" />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button type="primary" @click="onSubmit">
          添加
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style scoped>

</style>
