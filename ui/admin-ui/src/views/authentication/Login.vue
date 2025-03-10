<script lang="ts" setup>
import {ref} from 'vue';
import AccountLogin from './components/AccountLogin.vue';
import SmsLogin from './components/SmsLogin.vue';
import QrCodeLogin from './components/QrCodeLogin.vue';

const loginType = ref('account'); // 默认显示账号密码登录
</script>

<template>
  <div class="login-container">
    <!-- 左侧插图 -->
    <div class="login-left " style="background-color: #1890ff">

<!--      <img src="/src/assets/images/login-bg.png" alt="Login Background" />-->
      <h2>欢迎使用 Vben Admin</h2>
    </div>

    <!-- 右侧登录表单 -->
    <div class="login-right">
      <h1>欢迎回来 👋</h1>
      <p>请选择登录方式</p>

      <!-- 登录方式切换 -->
      <a-tabs v-model:activeKey="loginType" centered>
        <a-tab-pane key="account" tab="账号密码登录">
          <AccountLogin />
        </a-tab-pane>
        <a-tab-pane key="sms" tab="短信登录">
          <SmsLogin />
        </a-tab-pane>
        <a-tab-pane key="qrcode" tab="扫码登录">
          <QrCodeLogin />
        </a-tab-pane>
      </a-tabs>
    </div>

    <!-- 底部信息 -->
    <div class="footer">
    </div>
  </div>
</template>

<style lang="less" scoped>
.login-container {
  display: flex;
  height: 100vh;
}
.login-left {
  flex: 1;
  text-align: center;
}
.login-right {
  width: 400px;
  padding: 40px;
}
</style>
