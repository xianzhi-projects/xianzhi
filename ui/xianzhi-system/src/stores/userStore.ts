/*
 *  Copyright 2025 XianZhi Group .
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
import {defineStore} from 'pinia';
import type {TokenVO} from "@/api/authorization.ts";
import {computed, ref} from 'vue'

// 使用常量而非字符串直接引用，提高可维护性
const TOKEN_KEY = 'user_token';

// 工具函数：简化对 localStorage 的访问，并添加错误处理
function getStoredToken(): TokenVO | null {
  try {
    const item = localStorage.getItem(TOKEN_KEY);
    return item ? JSON.parse(item) : null;
  } catch (error) {
    console.error('Failed to parse stored token:', error);
    return null;
  }
}

function storeToken(token: TokenVO): void {
  try {
    localStorage.setItem(TOKEN_KEY, JSON.stringify(token));
  } catch (error) {
    console.error('Failed to store token:', error);
  }
}

function clearStoredToken(): void {
  try {
    localStorage.removeItem(TOKEN_KEY);
  } catch (error) {
    console.error('Failed to remove token:', error);
  }
}

export const useUserStore = defineStore('user', () => {
  // 使用 ref 来创建响应式的 tokenInfo
  const tokenInfo = ref<TokenVO | null>(getStoredToken());

  // 计算属性，检查是否登录
  const isLogin = computed(() => !!tokenInfo.value?.accessToken);

  // 设置 Token 信息
  function setToken(token: TokenVO) {
    tokenInfo.value = token;
    storeToken(token);
  }

  // 获取 Token 信息
  function getToken(): TokenVO | null {
    if (tokenInfo.value){
      return tokenInfo.value;
    }
    return getStoredToken();
  }

  // 删除 Token 信息
  function removeToken() {
    tokenInfo.value = null;
    clearStoredToken();
  }

  return { setToken, getToken, removeToken, isLogin };
});


