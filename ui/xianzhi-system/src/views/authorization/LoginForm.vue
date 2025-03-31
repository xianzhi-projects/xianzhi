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

<template>
  <div class="login-form">
    <h1 class="title">欢迎回来 <span class="wave-emoji">👋</span></h1>
    <p class="subtitle">请输入您的信息以开始管理您的项目</p>

    <el-form
        ref="formRef"
        :model="form"
        :rules="rules"
        label-position="top"
        @submit.prevent="handleLogin"
    >
      <!-- 用户名 -->
      <el-form-item prop="username">
        <el-input
            v-model="form.username"
            placeholder="请输入用户名"
            size="large"
            type="text"
        />
      </el-form-item>

      <!-- 密码 -->
      <el-form-item prop="password">
        <el-input
            v-model="form.password"
            placeholder="请输入密码"
            show-password
            size="large"
            type="password"
        />
      </el-form-item>


      <!-- 记住账号和忘记密码 -->
      <el-form-item>
        <div class="login-options">
          <el-checkbox v-model="rememberMe">记住账号</el-checkbox>
          <a class="forgot-password" href="#">忘记密码?</a>
        </div>
      </el-form-item>

      <!-- 登录按钮 -->
      <el-form-item>
        <el-button
            :loading="loading"
            class="login-button"
            native-type="submit"
            size="large"
            type="primary"
        >
          登录
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts" setup>
import {ref} from 'vue';
import {ElButton, ElCheckbox, ElForm, ElFormItem, ElInput, ElMessage} from 'element-plus';
import {useRouter} from 'vue-router';
import {passwordLogin, type PasswordLoginDTO} from "@/api/system/authorizationApi.ts";
import {useUserStore} from "@/stores/userStore.ts";


// 表单数据
const form = ref<PasswordLoginDTO>({
  username: '',
  password: '',
});


// 表单引用
const formRef = ref<InstanceType<typeof ElForm>>();

// 表单验证规则
const rules = {
  username: [
    { required: true, message: '请选择用户', trigger: 'change' },
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, message: '密码至少 6 个字符', trigger: 'blur' },
  ],
};

// 状态管理
const loading = ref(false);
const rememberMe = ref(false);

// 路由
const router = useRouter();
const userStore = useUserStore();

// 处理登录
const handleLogin = async () => {
  try {
    loading.value = true;
    await formRef.value?.validate();

    const response = await passwordLogin(form.value);
    if (response.code == '200') {
      ElMessage.success('登录成功');
      userStore.setToken(response.data);
      await router.push('/');
    } else {
      ElMessage.error(response.message);
    }
  } catch (error) {
    console.log(error)
  } finally {
    loading.value = false;
  }
};
</script>

<style lang="less" scoped>
.login-form {
  padding: 50px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  .title {
    font-size: 2.5rem;
    margin-bottom: 8px;
    text-align: left;
  }

  .subtitle {
    font-size: 14px;
    text-align: left;
    margin-bottom: 12px;
  }

  .el-form {
    width: 500px;
    height: 500px;
    padding: 20px 0;
    .el-form-item {
      margin-bottom: 32px;
    }
    .el-input{
      padding-bottom: 10px;
    }
  }

  .login-options {
    width: 100%;
    display: flex;
    justify-content: space-between;
    margin-bottom: 16px;
    .forgot-password {
      font-size: 14px;
      text-decoration: none;
    }
  }

  .login-button {
    width: 100%;
  }


  .login-footer {
    text-align: center;
    margin-top: 24px;
  }
}
</style>
