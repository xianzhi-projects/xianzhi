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

import {defineStore} from 'pinia'
import {computed, ref} from 'vue'
import {theme} from 'ant-design-vue'

/**
 * app 配置 开启持久化
 */
export const useAppStore = defineStore(
  'app',
  () => {
    // 颜色模式，默认为 'light'
    const darkMode = ref('light')

    // 根据 darkMode 设置 document 的 data-dark 属性
    const darkModeComp = computed(() => {
      document.documentElement.setAttribute('data-dark', darkMode.value)
      return darkMode.value
    })

    // 主题配置，根据 themeName 和 darkMode 生成
    const themeConfig = computed(() => {
      const colorPrimary =  '#0874f1'
      return {
        token: {
          colorPrimary,
          colorSuccess: '#1dc779',
          colorWarning: '#ffb302',
          colorError: '#cf4444',
          colorInfo: colorPrimary,
          wireframe: true
        },
        algorithm: darkMode.value === 'light' ? theme.defaultAlgorithm : theme.darkAlgorithm
      }
    })

    // 切换颜色模式
    const toggleDarkMode = () => {
      darkMode.value = darkMode.value === 'light' ? 'dark' : 'light'
    }

    return { themeConfig, darkMode, darkModeComp, toggleDarkMode}
  },
  {

    // persist: true // 开启持久化
  }
)

