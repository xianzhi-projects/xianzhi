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
  phone: '',
  smsCode: '',
});

const rules = {
  phone: [{ required: true, message: '请输入手机号' }],
  smsCode: [{ required: true, message: '请输入短信验证码' }],
};

const userStore = useUserStore();
const formRef = ref();

const sendSmsCode = () => {
  message.info('验证码已发送'); // 模拟发送
};

const handleLogin = async () => {
  try {
    await formRef.value.validate();
    await userStore.login({ type: 'sms', ...formState.value });
    message.success('登录成功');
    window.location.href = '/dashboard';
  } catch (error) {
    message.error('登录失败');
  }
};
</script>

<template>
  <a-form ref="formRef" :model="formState" :rules="rules" layout="vertical">
    <a-form-item label="手机号" name="phone">
      <a-input v-model:value="formState.phone" placeholder="请输入手机号" />
    </a-form-item>
    <a-form-item label="短信验证码" name="smsCode">
      <a-row :gutter="16">
        <a-col :span="14">
          <a-input v-model:value="formState.smsCode" placeholder="请输入验证码" />
        </a-col>
        <a-col :span="10">
          <a-button @click="sendSmsCode">获取验证码</a-button>
        </a-col>
      </a-row>
    </a-form-item>
    <a-form-item>
      <a-button block type="primary" @click="handleLogin">登录</a-button>
    </a-form-item>
  </a-form>
</template>
