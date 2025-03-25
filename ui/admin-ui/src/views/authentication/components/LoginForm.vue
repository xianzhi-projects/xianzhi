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

<script lang="ts" setup>
import {onMounted, reactive, ref, type UnwrapRef} from "vue";
import {getLoginCaptcha} from "@/api/captchaApi.ts";
import {passwordLogin, type PasswordLoginDTO} from "@/api/authorization.ts";
import {message} from "ant-design-vue";
import {LockOutlined, UserOutlined} from '@ant-design/icons-vue'
import {useUserStore} from '@/stores/userStore.ts'
import router from '@/router'
import {SUCCESS_CODE} from "@/constants/ResponseCodeConstant.ts";


// 当页面加载的时候就去自动刷新验证码
onMounted(() => {
  refreshCaptcha();
});

const formRef = ref()

// 验证码
const captchaSrc = ref<string>('')
// 密码登录入参
const loginDTO: UnwrapRef<PasswordLoginDTO> = reactive({
  username: '',
  password: '',
  captcha: '',
  captchaKey: '',
})


/**
 * 登录逻辑
 */
const handleLogin = () => {
  formRef.value.validate().then(async () => {
    try {
      const {data, code, message: str} = await passwordLogin(loginDTO)
      // 登录成功
      if (code === SUCCESS_CODE && data) {
        message.success(str)
        useUserStore().setUser(data)
        await router.push('/')
      } else {
        // 登录失败
        message.error(str)
      }
    } finally {
      // 都去刷新一次验证码
      await refreshCaptcha()
    }
  })
}
/**
 * 刷新验证码
 */
const refreshCaptcha = async () => {
  const {data} = await getLoginCaptcha()
  if (data) {
    captchaSrc.value = data.image
    loginDTO.captchaKey = data.key
  } else {
    message.error('获取验证码失败')
  }
}


</script>

<template>
  <div class="form_container">
    <a-form
      ref="formRef"
      :model="loginDTO"
      layout="vertical"
      @submit.prevent="handleLogin"
    >
      <h2 class="title">后台系统登录</h2>
      <a-form-item name="username">
        <a-input v-model:value="loginDTO.username" placeholder="请输入用户名">
          <template #prefix>
            <UserOutlined class="icon"/>
          </template>
        </a-input>
      </a-form-item>
      <a-form-item name="password">
        <a-input-password v-model:value="loginDTO.password" placeholder="请输入密码">
          <template #prefix>
            <LockOutlined class="icon"/>
          </template>
        </a-input-password>
      </a-form-item>
      <a-form-item name="captcha">
        <div class="captcha-container">
          <a-input
            v-model:value="loginDTO.captcha"
            class="captcha-input"
            placeholder="请输入验证码"
          />
          <img :src="captchaSrc" alt="验证码" class="captcha-image" @click="refreshCaptcha"/>
        </div>
      </a-form-item>
      <a-form-item>
        <a href="">忘记密码?</a>
      </a-form-item>
      <a-form-item>
        <a-button block class="login-button" html-type="submit" type="primary">
          登录
        </a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<style lang="less" scoped>
/* From Uiverse.io by zanina-yassine */
.form_container {
  width: fit-content;
  height: fit-content;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 15px;
  padding: 50px 40px 20px 40px;
  background-color: #ffffff;
  box-shadow: 0px 106px 42px rgba(0, 0, 0, 0.01),
  0px 59px 36px rgba(0, 0, 0, 0.05), 0px 26px 26px rgba(0, 0, 0, 0.09),
  0px 7px 15px rgba(0, 0, 0, 0.1), 0px 0px 0px rgba(0, 0, 0, 0.1);
  border-radius: 11px;
  font-family: "Inter", sans-serif;
}

.logo_container {
  box-sizing: border-box;
  width: 80px;
  height: 80px;
  background: linear-gradient(180deg, rgba(248, 248, 248, 0) 50%, #F8F8F888 100%);
  border: 1px solid #F7F7F8;
  filter: drop-shadow(0px 0.5px 0.5px #EFEFEF) drop-shadow(0px 1px 0.5px rgba(239, 239, 239, 0.5));
  border-radius: 11px;
}

.title_container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 10px;
}

.title {
  margin: 0;
  font-size: 1.25rem;
  font-weight: 700;
  color: #212121;
}

.subtitle {
  font-size: 0.725rem;
  max-width: 80%;
  text-align: center;
  line-height: 1.1rem;
  color: #8B8E98
}

.input_container {
  width: 100%;
  height: fit-content;
  position: relative;
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.icon {
  width: 20px;
  position: absolute;
  z-index: 99;
  left: 12px;
  bottom: 9px;
}

.input_label {
  font-size: 0.75rem;
  color: #8B8E98;
  font-weight: 600;
}

.input_field {
  width: auto;
  height: 40px;
  padding: 0 0 0 40px;
  border-radius: 7px;
  outline: none;
  border: 1px solid #e5e5e5;
  filter: drop-shadow(0px 1px 0px #efefef)
  drop-shadow(0px 1px 0.5px rgba(239, 239, 239, 0.5));
  transition: all 0.3s cubic-bezier(0.15, 0.83, 0.66, 1);
}

.input_field:focus {
  border: 1px solid transparent;
  box-shadow: 0px 0px 0px 2px #242424;
  background-color: transparent;
}

.sign-in_btn {
  width: 100%;
  height: 40px;
  border: 0;
  background: #115DFC;
  border-radius: 7px;
  outline: none;
  color: #ffffff;
  cursor: pointer;
}

.sign-in_ggl {
  width: 100%;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  background: #ffffff;
  border-radius: 7px;
  outline: none;
  color: #242424;
  border: 1px solid #e5e5e5;
  filter: drop-shadow(0px 1px 0px #efefef)
  drop-shadow(0px 1px 0.5px rgba(239, 239, 239, 0.5));
  cursor: pointer;
}

.sign-in_apl {
  width: 100%;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  background: #212121;
  border-radius: 7px;
  outline: none;
  color: #ffffff;
  border: 1px solid #e5e5e5;
  filter: drop-shadow(0px 1px 0px #efefef)
  drop-shadow(0px 1px 0.5px rgba(239, 239, 239, 0.5));
  cursor: pointer;
}

.separator {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 30px;
  color: #8B8E98;
}

.separator .line {
  display: block;
  width: 100%;
  height: 1px;
  border: 0;
  background-color: #e8e8e8;
}

.note {
  font-size: 0.75rem;
  color: #8B8E98;
  text-decoration: underline;
}


</style>
