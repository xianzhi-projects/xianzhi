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
import type {TokenVO} from '@/api/authorization.ts'

const TOKEN = 'token';
export const useUserStore = defineStore('user', () => {
  const tokenInfo = ref<TokenVO>()
  const isLogin = computed(() => {
    const token = JSON.parse(localStorage.getItem(TOKEN) || '{}')
    return token && token.accessToken
  })

  /**
   * 设置token信息
   */
  function setToken(token: TokenVO) {
    tokenInfo.value = token
    localStorage.setItem(TOKEN, JSON.stringify(token))
  }

  /**
   * 获取Token信息
   */
  function getToken() {
    return JSON.parse(localStorage.getItem(TOKEN) || '{}')
  }

  /**
   * 删除token
   */
  function removeToken() {
    return localStorage.removeItem(TOKEN)
  }

  return {setToken, getToken, removeToken, isLogin}
})


