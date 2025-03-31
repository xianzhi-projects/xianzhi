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
import type {AxiosRequestConfig, AxiosResponse, InternalAxiosRequestConfig} from 'axios'
import axios, {AxiosError} from 'axios'
import {refreshToken} from '@/api/authorizationApi.ts'
import {useUserStore} from '@/stores/userStore.ts'
import {ElMessage} from "element-plus";
import type {ResponseResult} from "@/types/core.ts";


// 自定义错误类
class ApiError extends Error {
  constructor(
    public code: string,
    message: string,
  ) {
    super(message)
  }
}

// 创建 Axios 实例
const instance = axios.create({
  baseURL: '/api',
  timeout: 5000,
  headers: {
    'Content-Type': 'application/json',
    Accept: 'application/json',
  },
})

let isRefreshing = false
let refreshSubscribers: ((accessToken: string) => void)[] = []

/**
 * 刷新 token 并重新发送原始请求
 * @param error AxiosError 对象
 */
const refreshTokenAndRetryRequest = async (error: AxiosError): Promise<AxiosResponse> => {
  // 检查 error.config 是否存在
  if (!error.config) {
    return Promise.reject(new Error('Request configuration is undefined'))
  }

  // 如果正在刷新 token，等待刷新完成后再重试
  if (isRefreshing) {
    return new Promise((resolve) => {
      // 将重试请求的回调存储起来
      refreshSubscribers.push((accessToken) => {
        // 更新原始请求的 Authorization header，并重试请求
        error.config!.headers.Authorization = `Bearer ${accessToken}`
        resolve(axios.request(error.config!))
      })
    })
  }

  // 如果没有正在刷新 token，则设置为正在刷新
  isRefreshing = true

  try {
    const token = useUserStore().getToken();
    if (token) {
      // 刷新 token
      const response = await refreshToken(token.refreshToken)
      const newAccessToken = response.data?.accessToken

      if (newAccessToken) {
        // 更新原始请求的 Authorization header
        error.config.headers.Authorization = `Bearer ${newAccessToken}`

        // 通知所有等待的请求重试
        refreshSubscribers.forEach((cb) => cb(newAccessToken))
        refreshSubscribers = []
        // 重新发送原始请求
        return axios.request(error.config)
      }

      // 如果刷新失败，跳转到登录页或进行其他处理
      redirectToLogin()
      return Promise.reject(error)
    } else {
      redirectToLogin()
    }

  } catch (refreshError) {
    // 如果刷新 token 失败，跳转到登录页
    redirectToLogin()
    return Promise.reject(refreshError)
  } finally {
    // 刷新完成，重置状态
    isRefreshing = false
  }
}

/**
 * 跳转到登录页面
 */
const redirectToLogin = () => {
  window.location.href = '/login'
}

/**
 * 处理请求错误
 * @param error AxiosError 对象
 */
const handleRequestError = (error: AxiosError) => {
  console.error('Request Error:', error) // 打印请求错误
  return Promise.reject(error)
}

/**
 * 处理响应错误
 * @param error AxiosError 对象
 */
const handleResponseError = (error: AxiosError) => {
  if (error.response?.status === 401) {
    return refreshTokenAndRetryRequest(error)
  }
  return Promise.reject(error)
}

/**
 * 处理响应成功
 * @param response AxiosResponse 对象
 */
const handleResponseSuccess = (response: AxiosResponse) => {
  const result = response.data
  const code = result.code
  if (code === '200') {
    return response
  }

  if (code === '401') {
    // 处理 token 过期的情况，刷新 token 并重试原始请求
    return refreshTokenAndRetryRequest(response.config)
      .then((retryResponse) => {
        return retryResponse // 返回重试后的响应
      })
      .catch((error) => {
        // 如果刷新 token 失败，跳转到登录页面或其他处理逻辑
        redirectToLogin()
        return Promise.reject(error)
      })
  }
  if (code == '503') {
    ElMessage.error(result.message)
    return response
  }
  return response
}

// 请求拦截器
instance.interceptors.request.use((config: InternalAxiosRequestConfig) => {
  // 在发送请求之前做些什么
  // 比如在这里可以加上 token
  const tokenInfo = useUserStore().getToken();
  if (tokenInfo && tokenInfo.accessToken) {
    config.headers.Authorization = `Bearer ${tokenInfo.accessToken}`
  }
  return config
}, handleRequestError)

// 响应拦截器
instance.interceptors.response.use(handleResponseSuccess, handleResponseError)

class HttpRequest {
  public async request<T>(options: AxiosRequestConfig): Promise<ResponseResult<T>> {
    return instance
      .request<ResponseResult<T>>(options)
      .then((response: AxiosResponse<ResponseResult<T>>) => {
        return response.data
      })
  }
}

const http = new HttpRequest()
export default http


