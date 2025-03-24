<template>
  <div class="login-container">
    <a-form
      :label-col="{ span: 8 }"
      :model="formState"
      :wrapper-col="{ span: 16 }"
      autocomplete="off"
      name="basic"
      @finish="onFinish"
      @finishFailed="onFinishFailed"
      class="login-form"
    >
      <a-form-item
        :rules="[{ required: true, message: 'Please input your username!' }]"
        label="用户名"
        name="username"
      >
        <a-input v-model:value="formState.username" class="input"/>
      </a-form-item>

      <a-form-item
        :rules="[{ required: true, message: 'Please input your password!' }]"
        label="密码"
        name="password"
      >
        <a-input-password v-model:value="formState.password" class="input"/>
      </a-form-item>
      <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
        <a-button html-type="submit" type="primary">点击登录</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>
<script setup>
import {reactive} from 'vue';

const formState = reactive({
  username: '',
  password: '',
  remember: true,
});
const onFinish = values => {
  console.log('Success:', values);
};
const onFinishFailed = errorInfo => {
  console.log('Failed:', errorInfo);
};
</script>
<style lang="less" scoped>
.login-container {
  width: 500px;
  height: 320px;
  background-color: #76767c;
  margin: 15% auto;
  padding-top: 100px;
  .login-form{
    background-color: #1890ff;
    padding: 10px;
  }
  .input {
    width: 300px;
  }
}
</style>
