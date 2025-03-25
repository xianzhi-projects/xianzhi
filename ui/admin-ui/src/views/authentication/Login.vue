<template>

  <a-row :xs="8">
    <a-col :span="16" class="login-left">18</a-col>
    <a-col :span="8" class="login-right">
      <LoginForm/>
    </a-col>
  </a-row>

</template>
<script setup>
import {reactive} from 'vue';
import LoginForm from "@/views/authentication/components/LoginForm.vue";

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
.login-left {
  background-image: url("@/assets/images/back.webp");
}

.login-right {
  background-color: rgb(232,232,232);
  height: 100vh;
}
</style>
