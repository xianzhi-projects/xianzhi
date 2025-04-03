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

<!-- src/views/Login.vue -->
<template>
  <div class="login-page">
    <div class="login-container">
      <div class="login-header">
        <h1>欢迎回来 <span class="wave-emoji">👋</span></h1>
        <p>请输入您的信息以开始管理您的项目</p>
      </div>

      <a-form
        ref="formRef"
        :model="form"
        :rules="rules"
        class="login-form"
        layout="vertical"
        @finish="handleLogin"
      >
        <!-- 用户名 -->
        <a-form-item name="username">
          <a-select
            v-model:value="form.username"
            :options="usernameOptions"
            placeholder="请选择用户"
            size="large"
          />
        </a-form-item>

        <!-- 密码 -->
        <a-form-item name="password">
          <a-input-password
            v-model:value="form.password"
            placeholder="请输入密码"
            size="large"
          />
        </a-form-item>

        <!-- 滑动验证码 -->
        <a-form-item name="captcha">
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
        </a-form-item>

        <!-- 记住账号和忘记密码 -->
        <a-form-item>
          <div class="login-options">
            <a-checkbox v-model:checked="rememberMe">记住账号</a-checkbox>
            <a class="forgot-password" href="#">忘记密码?</a>
          </div>
        </a-form-item>

        <!-- 登录按钮 -->
        <a-form-item>
          <a-button
            :disabled="!isVerified"
            :loading="loading"
            block
            class="login-button"
            html-type="submit"
            size="large"
            type="primary"
          >
            登录
          </a-button>
        </a-form-item>

        <!-- 其他登录方式 -->
        <div class="other-login">
          <span>其他登录方式</span>
          <div class="login-icons">
            <a class="icon" href="#"><wechat-outlined /></a>
            <a class="icon" href="#"><dingding-outlined /></a>
            <a class="icon" href="#"><github-outlined /></a>
            <a class="icon" href="#"><google-outlined /></a>
          </div>
        </div>
      </a-form>

      <!-- 底部链接 -->
      <div class="login-footer">
        <a href="#">还没有账号? 创建账号</a>
        <a class="team-link" href="#">创建团队</a>
      </div>
    </div>

    <!-- 版权信息 -->
    <div class="copyright">
      Copyright © 2024 Vben
    </div>
  </div>
</template>

<script lang="ts" setup>
import {ref} from 'vue';
import {FormInstance, message} from 'ant-design-vue';
import {useRouter} from 'vue-router';
import {
  DingdingOutlined,
  GithubOutlined,
  GoogleOutlined,
  WechatOutlined,
} from '@ant-design/icons-vue';

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
const formRef = ref<FormInstance>();

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
    const maxSlide = trackWidth - 40; // 滑块宽度为 40px
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
      message.success('登录成功');
      if (rememberMe.value) {
        localStorage.setItem('username', form.value.username);
      }
      router.push('/dashboard');
    } else {
      message.error('用户名或密码错误');
    }
  } catch (error) {
    message.error('登录失败，请检查输入或完成验证');
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
@primary-color: #6b7ecc;
@success-color: #52c41a;
@text-color: #333;
@text-color-secondary: #666;
@border-color: #d9d9d9;
@background-color: #f0f2f5;
@shadow: 0 2px 8px rgba(0, 0, 0, 0.15);

.login-page {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: @background-color;
  position: relative;
}

.login-container {
  width: 100%;
  max-width: 400px;
  padding: 32px;
  //background: #fff;
  border-radius: 8px;
  box-shadow: @shadow;
}

.login-header {
  text-align: center;
  margin-bottom: 24px;

  h1 {
    font-size: 24px;
    color: @text-color;
    margin-bottom: 8px;
  }

  .wave-emoji {
    font-size: 24px;
  }

  p {
    color: @text-color-secondary;
    font-size: 14px;
  }
}

.login-form {
  margin-top: 16px;
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

.copyright {
  position: absolute;
  bottom: 16px;
  color: @text-color-secondary;
  font-size: 12px;
}
</style>
