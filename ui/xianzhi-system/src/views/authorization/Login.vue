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
    <!-- 左侧图片 -->
    <div class="login-left">
      <div class="image-wrapper">
<!--        <img src="@/assets/login-bg.jpg" alt="Login Background" />-->
        <div class="overlay">
          <h2>Vben Admin</h2>
          <p>一个高效、现代化的管理后台系统</p>
        </div>
      </div>
    </div>

    <!-- 右侧登录表单 -->
    <div class="login-right">
      <login-form />
      <div class="copyright">
        Copyright © 2024 Vben
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import LoginForm from './components/LoginForm.vue';
</script>

<style lang="less" scoped>
// 定义变量
@background-color: #fff;
@text-color: #fff;
@text-color-secondary: #d9d9d9;

.login-page {
  display: flex;
  min-height: 100vh;
  background: @background-color;
  .login-left {
    flex: 2;
    position: relative;
    background: #000;
    .image-wrapper {
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      overflow: hidden;
      img {
        width: 100%;
        height: 100%;
        object-fit: cover;
        opacity: 0.8;
      }
      .overlay {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        text-align: center;
        color: @text-color;

        h2 {
          font-size: 36px;
          margin-bottom: 16px;
        }

        p {
          font-size: 16px;
          color: @text-color-secondary;
        }
      }
    }
  }

  .login-right {
    flex: 1;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 40px;
    background: #fff;
    position: relative;

    .copyright {
      position: absolute;
      bottom: 10px;
      color: @text-color-secondary;
      font-size: 12px;
    }
  }

  // 响应式布局
  @media (max-width: 768px) {
    flex-direction: column;

    .login-left {
      flex: none;
      height: 300px;
    }

    .login-right {
      flex: none;
      padding: 20px;
    }
  }
}
</style>