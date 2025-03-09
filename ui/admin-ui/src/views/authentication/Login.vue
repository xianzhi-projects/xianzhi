<template>
  <div class="login-page">
    <div class="background"></div>
    <div class="login-container">
      <a-form
        ref="formRef"
        :model="passwordLoginDTO"
        :rules="loginRules"
        layout="vertical"
        @submit="handleLogin"
      >
        <h2 class="title">后台系统登录</h2>
        <a-form-item name="username">
          <a-input v-model:value="passwordLoginDTO.username" placeholder="请输入用户名">
            <template #prefix>
              <UserOutlined class="site-form-item-icon" />
            </template>
          </a-input>
        </a-form-item>
        <a-form-item name="password">
          <a-input-password v-model:value="passwordLoginDTO.password" placeholder="请输入密码">
            <template #prefix>
              <LockOutlined class="site-form-item-icon" />
            </template>
          </a-input-password>
        </a-form-item>
        <a-form-item name="captcha">
          <div class="captcha-container">
            <a-input
              v-model:value="passwordLoginDTO.captcha"
              placeholder="请输入验证码"
              width="80%"
            />
            <img :src="captchaSrc" alt="code" class="captcha-image" @click="refreshCaptcha" />
          </div>
        </a-form-item>
        <a-form-item>
          <a-form-item name="remember" no-style>
            <a-checkbox v-model:checked="passwordLoginDTO.remember" class="x-left"
            >记住我
            </a-checkbox>
          </a-form-item>
          <a class="x-right" href="">忘记密码?</a>
        </a-form-item>
        <a-form-item>
          <a-button block type="primary" @click="handleLogin">登录</a-button>
        </a-form-item>
      </a-form>
    </div>
  </div>
</template>

<script lang="ts" setup>
import router from '@/router'
import {onMounted, reactive, ref, type UnwrapRef} from 'vue'
import {LockOutlined, UserOutlined} from '@ant-design/icons-vue'
import {message} from 'ant-design-vue'
import {passwordLogin, type PasswordLoginDTO} from '@/api/authorization.ts'
import loginRules from '@/views/authentication/index.ts'
import {useUserStore} from '@/stores/userStore.ts'
import {getLoginCaptcha} from '@/api/captchaApi.ts'

const formRef = ref()
const captchaSrc = ref<string>('')
const passwordLoginDTO: UnwrapRef<PasswordLoginDTO> = reactive({
  remember: false,
  username: '',
  password: '',
  // captcha: '',
  captchaKey: '',
})
/**
 * 登录逻辑
 */
const handleLogin = () => {
  // 检查字段格式是否合法
  formRef.value.validate().then(async () => {
    try {
      // 密码登录
      const { data, code, message: str } = await passwordLogin(passwordLoginDTO)
      if (code === '200' && data) {
        message.success('登录成功')
        useUserStore().setUser(data)
        // 跳转到首页
        await router.push('/')
      } else {
        message.error(str)
      }
    } finally {
      await refreshCaptcha()
    }
  })
}
/**
 * 当页面加载的时候获取一个新的验证码
 */
onMounted(() => {
  refreshCaptcha()
})
/**
 * 获取验证码
 */
const refreshCaptcha = async () => {
  const { data } = await getLoginCaptcha()
  if (data) {
    captchaSrc.value = data.image
    passwordLoginDTO.captchaKey = data.key
  } else {
    message.error('获取验证码失败')
  }
}
</script>

<style lang="less" scoped>
.login-page {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(135deg, #0a2a52, #1a4a7a);
  position: relative;
  overflow: hidden;

  .background {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-image: url('@/assets/images/back.webp');
    background-size: cover;
    background-position: center;
    filter: blur(4px);
    opacity: 0.8;
  }

  .login-container {
    background-color: rgba(255, 255, 255, 0.9);
    padding: 40px;
    width: 420px;
    border-radius: 12px;
    box-shadow: 0 8px 24px rgba(0, 0, 0, 0.15);
    text-align: left;
    position: relative;
    z-index: 1;
    backdrop-filter: blur(6px);
    border: 1px solid rgba(255, 255, 255, 0.2);
    animation: fadeInUp 0.6s ease;

    @keyframes fadeInUp {
      from {
        opacity: 0;
        transform: translateY(20px);
      }
      to {
        opacity: 1;
        transform: translateY(0);
      }
    }

    .title {
      font-size: 24px;
      font-weight: 700;
      color: #333;
      margin-bottom: 24px;
      text-align: center;
    }

    .ant-input,
    .ant-input-password {
      border-radius: 6px;
      border: 1px solid #d9d9d9;
      transition: all 0.3s ease;

      &:focus {
        border-color: #347af7;
        box-shadow: 0 0 0 2px rgba(52, 122, 247, 0.2);
      }
    }

    .ant-btn-primary {
      background: linear-gradient(135deg, #347af7, #6a5af9);
      border: none;
      border-radius: 6px;
      transition: all 0.3s ease;

      &:hover {
        opacity: 0.9;
        transform: translateY(-2px);
      }

      &:active {
        transform: translateY(0);
      }
    }

    .captcha-container {
      display: flex;
      align-items: center;
      justify-content: space-between;

      .ant-input {
        width: 60%;
      }

      .captcha-image {
        width: 120px;
        height: 36px;
        margin-left: 10px;
        cursor: pointer;
        border-radius: 6px;
        border: 1px solid #d9d9d9;
        transition: all 0.3s ease;

        &:hover {
          border-color: #347af7;
          box-shadow: 0 0 0 2px rgba(52, 122, 247, 0.2);
        }
      }
    }

    .ant-form-item {
      margin-bottom: 16px;

      .x-left {
        float: left;
      }

      .x-right {
        float: right;
        color: #347af7;
        text-decoration: none;
        transition: all 0.3s ease;

        &:hover {
          text-decoration: underline;
          opacity: 0.8;
        }
      }
    }
  }
}
</style>
