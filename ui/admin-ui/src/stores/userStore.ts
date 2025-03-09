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

export const useUserStore = defineStore('user', () => {
  const userInformation = ref<TokenVO>()
  const isLogin = computed(() => {
    const user = JSON.parse(localStorage.getItem('user') || '{}')
    return user && user.accessToken
  })

  /**
   * 用户信息
   */
  function setUser(user: TokenVO) {
    userInformation.value = user
    localStorage.setItem('user', JSON.stringify(user))
  }

  /**
   * 获取用户信息
   */
  function getUser() {
    return JSON.parse(localStorage.getItem('user') || '{}')
  }

  function removeUser() {
    return localStorage.removeItem('user')
  }

  return { userInformation, setUser, getUser, removeUser, isLogin }
})


