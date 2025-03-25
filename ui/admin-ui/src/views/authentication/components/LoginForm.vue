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
  <div class="login_container">
    <a-form class="form_container"
      ref="formRef"
      :model="loginDTO"
      layout="vertical"
      @submit.prevent="handleLogin"
    >

      <div class="form_item">
        <h2 class="title">先知 | 研发效能平台</h2>
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
        <a-form-item>
          <a href="">忘记密码?</a>
        </a-form-item>
        <a-form-item>
          <a-button block class="login-button" html-type="submit" type="primary">
            登录
          </a-button>
        </a-form-item>
      </div>

    </a-form>
  </div>
</template>

<style lang="less" scoped>
/* From Uiverse.io by zanina-yassine */
.login_container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f5f5;
  .form_container {
    width: 500px;
    height: 800px;
    background-color: white;
    border-radius: 15px;
    padding: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
    .title{
      font-size: 25px;
      font-weight: 900;
      text-align: center;
    }
    .form_item{
      width: 100%;
    }
  }
}






</style>
