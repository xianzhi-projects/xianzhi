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
import {computed, ref} from 'vue'
import {defineStore} from 'pinia'
import type {TokenVO} from '@/api/system/authorizationApi.ts'

const TOKEN_KEY = 'token'

export const useUserStore = defineStore('user', () => {
  const tokenInfo = ref<TokenVO | null>(null)





  // 使用计算属性检查登录状态
  const isLogin = computed(() => !!tokenInfo.value?.accessToken)

  // 从 localStorage 获取 token 的辅助函数
  const getStoredToken = (): TokenVO | null => {
    try {
      const stored = localStorage.getItem(TOKEN_KEY)
      return stored ? JSON.parse(stored) : null
    } catch {
      return null
    }
  }

  // 初始化时加载存储的 token
  tokenInfo.value = getStoredToken()

  // 设置 token
  const setToken = (token: TokenVO | null) => {
    tokenInfo.value = token
    localStorage.setItem(TOKEN_KEY, JSON.stringify(token))
  }

  // 获取 token
  const getToken = () => tokenInfo.value

  // 移除 token
  const removeToken = () => {
    tokenInfo.value = null
    localStorage.removeItem(TOKEN_KEY)
  }

  return {
    tokenInfo,
    isLogin,
    setToken,
    getToken,
    removeToken
  }
})
