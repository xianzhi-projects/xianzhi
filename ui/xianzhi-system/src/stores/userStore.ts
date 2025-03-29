/*
 *  Copyright 2025 XianZhi Group.
 *  Licensed under the Apache License, Version 2.0
 *  http://www.apache.org/licenses/LICENSE-2.0
 */
import {computed, ref} from 'vue'
import {defineStore} from 'pinia'
import type {TokenVO} from '@/api/authorization.ts'

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
