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
import {ref} from 'vue';
import {message} from 'ant-design-vue';
import {useUserStore} from '@/stores/userStore';

const formState = ref({
  role: 'Super',
  username: '',
  password: '',
  captcha: '',
});


const rules = {
  username: [{ required: true, message: '请输入用户名' }],
  password: [{ required: true, message: '请输入密码' }],
  captcha: [{ required: true, message: '请输入验证码' }],
};

const captchaImage = ref('/src/assets/images/captcha.png');
const refreshCaptcha = () => {
  captchaImage.value = '/src/assets/images/captcha.png'; // 模拟刷新
};

const userStore = useUserStore();
const formRef = ref();

const handleLogin = async () => {
  try {
    await formRef.value.validate();
    await userStore.login({ type: 'account', ...formState.value });
    message.success('登录成功');
    window.location.href = '/dashboard';
  } catch (error) {
    message.error('登录失败');
    refreshCaptcha();
  }
};
</script>

<template>
  <a-form ref="formRef" :model="formState" :rules="rules" layout="vertical">
    <a-form-item label="用户名" name="username">
      <a-input v-model:value="formState.username" placeholder="请输入用户名" />
    </a-form-item>
    <a-form-item label="密码" name="password">
      <a-input-password v-model:value="formState.password" placeholder="请输入密码" />
    </a-form-item>
    <a-form-item label="验证码" name="captcha">
      <a-row :gutter="16">
        <a-col :span="14">
          <a-input v-model:value="formState.captcha" placeholder="请输入验证码" />
        </a-col>
        <a-col :span="10">
          <img :src="captchaImage" style="width: 100px;" @click="refreshCaptcha" />
        </a-col>
      </a-row>
    </a-form-item>
    <a-form-item>
      <a-button block type="primary" @click="handleLogin">登录</a-button>
    </a-form-item>
  </a-form>
</template>
