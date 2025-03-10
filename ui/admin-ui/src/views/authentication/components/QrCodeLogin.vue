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
import {useUserStore} from '@/stores/userStore';
import {message} from 'ant-design-vue';

const userStore = useUserStore();

const handleQrLogin = () => {
  message.info('请使用手机扫描二维码登录');
  // 实际项目中此处应调用扫码登录逻辑
};
</script>

<template>
  <div class="qrcode-login">
<!--    <img src="/src/assets/images/qrcode.png" alt="QR Code" style="width: 200px;" />-->
    <p>请使用手机扫描二维码登录</p>
    <a-button block type="primary" @click="handleQrLogin">扫码登录</a-button>
  </div>
</template>

<style lang="less" scoped>
.qrcode-login {
  text-align: center;
  padding: 20px;
}
</style>
