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

      <!-- 滑动验证码 -->
      <el-form-item prop="captcha">
        <div class="slider-captcha">
          <div ref="sliderTrack" class="slider-track" @mousedown="startSlide">
            <div
                :class="{ 'slider-block-success': isVerified }"
                :style="{ left: sliderPosition + 'px' }"
                class="slider-block"
            ></div>
            <span v-if="!isVerified" class="slider-text">请滑动验证</span>
            <span v-if="isVerified" class="slider-text-success">验证通过</span>
          </div>
        </div>
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
            :disabled="!isVerified"
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

// 定义表单数据接口
interface LoginForm {
  username: string;
  password: string;
  captcha?: boolean;
}

// 表单数据
const form = ref<LoginForm>({
  username: '',
  password: '',
});

// 用户名选项
const usernameOptions = [
  { label: 'Super', value: 'Super' },
  { label: 'vben', value: 'vben' },
];

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
  captcha: [
    {
      required: true,
      validator: () => (isVerified.value ? Promise.resolve() : Promise.reject('请完成滑动验证')),
      trigger: 'submit',
    },
  ],
};

// 状态管理
const loading = ref(false);
const rememberMe = ref(false);
const isVerified = ref(false);
const sliderPosition = ref(0);
const sliderTrack = ref<HTMLElement | null>(null);
let isDragging = false;

// 路由
const router = useRouter();

// 开始滑动
const startSlide = (event: MouseEvent) => {
  if (isVerified.value) return;
  isDragging = true;
  const startX = event.clientX - sliderPosition.value;

  const moveHandler = (moveEvent: MouseEvent) => {
    if (!isDragging) return;
    const trackWidth = sliderTrack.value?.offsetWidth || 0;
    const maxSlide = trackWidth - 40;
    let newPosition = moveEvent.clientX - startX;

    if (newPosition < 0) newPosition = 0;
    if (newPosition > maxSlide) newPosition = maxSlide;

    sliderPosition.value = newPosition;

    if (newPosition >= maxSlide - 5) {
      isVerified.value = true;
      isDragging = false;
      document.removeEventListener('mousemove', moveHandler);
      document.removeEventListener('mouseup', upHandler);
    }
  };

  const upHandler = () => {
    isDragging = false;
    if (!isVerified.value) {
      sliderPosition.value = 0;
    }
    document.removeEventListener('mousemove', moveHandler);
    document.removeEventListener('mouseup', upHandler);
  };

  document.addEventListener('mousemove', moveHandler);
  document.addEventListener('mouseup', upHandler);
};

// 处理登录
const handleLogin = async () => {
  try {
    loading.value = true;
    await formRef.value?.validate();

    const response = await mockLogin(form.value);
    if (response.success) {
      ElMessage.success('登录成功');
      if (rememberMe.value) {
        localStorage.setItem('username', form.value.username);
      }
      router.push('/dashboard');
    } else {
      ElMessage.error('用户名或密码错误');
    }
  } catch (error) {
    ElMessage.error('登录失败，请检查输入或完成验证');
  } finally {
    loading.value = false;
  }
};

// 模拟登录请求
const mockLogin = (form: LoginForm): Promise<{ success: boolean }> => {
  return new Promise((resolve) => {
    setTimeout(() => {
      if (form.username === 'vben' && form.password === '123456') {
        resolve({ success: true });
      } else {
        resolve({ success: false });
      }
    }, 1000);
  });
};
</script>

<style lang="less" scoped>
// 定义变量
@primary-color: #2f54eb;
@success-color: #52c41a;
@text-color: #333;
@text-color-secondary: #666;
@border-color: #d9d9d9;

.login-form {
  .title {
    font-size: 2.25rem;
    color: @text-color;
    margin-bottom: 8px;
    text-align: left;
    .wave-emoji {
      font-size: 24px;
    }
  }

  .subtitle {
    color: @text-color-secondary;
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
      width: 100%;
      border-radius: 50px;
    }
  }

  // 滑动验证码样式
  .slider-captcha {
    width: 100%;
    height: 40px;
    position: relative;
    user-select: none;

    .slider-track {
      width: 100%;
      height: 100%;
      background: #f5f5f5;
      border: 1px solid @border-color;
      border-radius: 4px;
      position: relative;
      cursor: pointer;
      overflow: hidden;

      .slider-block {
        width: 40px;
        height: 100%;
        background: @primary-color;
        position: absolute;
        top: 0;
        left: 0;
        transition: left 0.2s ease-out;

        &.slider-block-success {
          background: @success-color;
        }
      }

      .slider-text,
      .slider-text-success {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        color: @text-color-secondary;
        font-size: 14px;
        pointer-events: none;
      }

      .slider-text-success {
        color: @success-color;
      }
    }
  }

  .login-options {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 16px;

    .forgot-password {
      color: @primary-color;
      font-size: 14px;
    }
  }

  .login-button {
    width: 100%;
    background: @primary-color;
    border-color: @primary-color;
  }

  .other-login {
    text-align: center;
    margin-top: 24px;

    span {
      color: @text-color-secondary;
      font-size: 14px;
    }

    .login-icons {
      display: flex;
      justify-content: center;
      gap: 16px;
      margin-top: 8px;

      .icon {
        font-size: 24px;
        color: @text-color-secondary;

        &:hover {
          color: @primary-color;
        }
      }
    }
  }

  .login-footer {
    text-align: center;
    margin-top: 24px;

    a {
      color: @primary-color;
      font-size: 14px;
      margin: 0 8px;
    }

    .team-link {
      margin-left: 16px;
    }
  }
}
</style>
