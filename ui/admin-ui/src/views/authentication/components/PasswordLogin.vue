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
</template>

<style scoped>

</style>
