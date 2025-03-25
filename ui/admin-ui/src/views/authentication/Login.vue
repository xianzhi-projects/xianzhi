<template>

  <a-row :xs="8">
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
.login-left {
  background-image: url("@/assets/images/back.webp") ;
  background-repeat: no-repeat;
  background-size: cover;
}

.login-right {
  background-color: rgb(232,232,232);
  height: 100vh;
}
</style>
