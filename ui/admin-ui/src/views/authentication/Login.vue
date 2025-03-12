<template>
  <div class="app-container">
    <LoginForm />
  </div>
</template>

<script lang="ts">


import {defineComponent} from "vue";
import LoginForm from "@/views/authentication/components/LoginForm.vue";

export default defineComponent({
  components: {LoginForm}
})
</script>

<style scoped>
.app-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100vw;
  background-color: #f0f2f5;
}
</style>
