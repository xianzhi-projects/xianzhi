<template>

  <a-row :xs="8" class="login">
    <a-col :span="16" class="login-left">18</a-col>
    <a-col :span="8" class="login-right">
      <LoginForm/>
    </a-col>
  </a-row>

</template>
<script setup>
import LoginForm from "@/views/authentication/components/LoginForm.vue";
</script>
<style lang="less" scoped>
.login{
  //background-color: #f1f1f1;
  .login-left {
    background-image: url("@/assets/images/back.webp") ;
    background-repeat: no-repeat;
    background-size: cover;
  }
  .login-right {
    height: 100vh;
  }
}



</style>
