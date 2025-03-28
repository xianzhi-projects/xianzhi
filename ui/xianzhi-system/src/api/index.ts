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
import axios, {
  AxiosError,
  type AxiosRequestConfig,
  type AxiosResponse,
  type InternalAxiosRequestConfig
} from 'axios';
import type {TokenVO} from '@/api/authorization.ts';
import {refreshToken as refreshTokenAPI} from '@/api/authorization.ts';
import {useUserStore} from '@/stores/userStore.ts';
import {Message} from "@element-plus/icons-vue";


export const BASE_SERVER = import.meta.env.VITE_BASE_SERVER;


/**
 * 响应数据结构
 */
export interface ResponseResult<T> {
  message: string;
  code: string;
  data: T | null;
  traceId: string;
}

/**
 * 分页查询返回数据结构
 */
export interface ListResult<T> {
  total: number;
  list: T[];
}

/**
 * 自定义 API 错误类
 */
class ApiError extends Error {
  constructor(public code: string, message: string) {
    super(message);
    this.name = 'ApiError';
  }
}

/**
 * 创建并配置 Axios 实例
 */
const createAxiosInstance = (userStore: ReturnType<typeof useUserStore>) => {
  const instance = axios.create({
    baseURL: '/api',
    timeout: 5000,
    headers: {
      'Content-Type': 'application/json',
      Accept: 'application/json',
    },
  });

  const publicPaths = ['/oauth2/token'];

  const tokenRefreshManager = {
    isRefreshing: false,
    subscribers: [] as ((accessToken: string) => void)[],

    async refresh(currentRefreshToken: string): Promise<TokenVO> {
      const response = await refreshTokenAPI(currentRefreshToken);
      const newTokens = response.data;
      if (!newTokens?.accessToken) {
        throw new ApiError('TOKEN_REFRESH_FAILED', 'Failed to refresh access token');
      }
      return {
        accessToken: newTokens.accessToken,
        refreshToken: newTokens.refreshToken || currentRefreshToken,
      };
    },

    async refreshAndRetry(error: AxiosError): Promise<AxiosResponse> {
      const config = error.config;
      if (!config) {
        throw new ApiError('CONFIG_ERROR', 'Request configuration is missing');
      }

      if (this.isRefreshing) {
        return new Promise((resolve) => {
          this.subscribers.push((newToken) => {
            config.headers!.Authorization = `Bearer ${newToken}`;
            resolve(instance.request(config));
          });
        });
      }

      this.isRefreshing = true;
      try {
        const currentRefreshToken = userStore.getToken().refreshToken;
        const newTokens = await this.refresh(currentRefreshToken);
        userStore.setToken(newTokens);
        config.headers!.Authorization = `Bearer ${newTokens.accessToken}`;
        this.subscribers.forEach((callback) => callback(newTokens.accessToken));
        this.subscribers = [];
        return instance.request(config);
      } catch (refreshError) {
        redirectToLogin();
        throw refreshError;
      } finally {
        this.isRefreshing = false;
      }
    },
  };

  instance.interceptors.request.use(
    (config: InternalAxiosRequestConfig) => {
      const token = userStore.getToken().accessToken;
      if (token && !publicPaths.includes(config.url || '')) {
        config.headers.Authorization = `Bearer ${token}`;
      }
      return config;
    },
    (error: AxiosError) => {
      console.error('Request Error:', error);
      throw error;
    },
  );

  instance.interceptors.response.use(
    (response: AxiosResponse<ResponseResult<unknown>>) => {
      const { code, message: msg } = response.data;
      if (code === '200') {
        return response;
      }
      if (['503', '-1'].includes(code)) {
        Message.error(msg);
      }
      throw new ApiError(code, msg);
    },
    (error: AxiosError) => {
      if (error.response?.status === 401) {
        return tokenRefreshManager.refreshAndRetry(error);
      }
      throw error;
    },
  );

  return instance;
};

/**
 * 跳转到登录页面
 */
const redirectToLogin = () => {
  window.location.href = '/login';
};

/**
 * HTTP 请求类
 */
class HttpRequest {
  private instance: ReturnType<typeof createAxiosInstance>;

  constructor(userStore: ReturnType<typeof useUserStore>) {
    this.instance = createAxiosInstance(userStore);
  }

  async request<T>(config: AxiosRequestConfig): Promise<ResponseResult<T>> {
    const response = await this.instance.request<ResponseResult<T>>(config);
    return response.data;
  }

  get<T>(url: string, config?: AxiosRequestConfig): Promise<ResponseResult<T>> {
    return this.request<T>({ method: 'GET', url, ...config });
  }

  post<T>(url: string, data?: any, config?: AxiosRequestConfig): Promise<ResponseResult<T>> {
    return this.request<T>({ method: 'POST', url, data, ...config });
  }
}

// 延迟初始化的 HTTP 客户端
let httpInstance: HttpRequest | null = null;

const getHttpClient = () => {
  if (!httpInstance) {
    const userStore = useUserStore();
    httpInstance = new HttpRequest(userStore);
  }
  return httpInstance;
};

// 导出单例实例（代理模式）
export const http = {
  request<T>(config: AxiosRequestConfig) {
    return getHttpClient().request<T>(config);
  },
  get<T>(url: string, config?: AxiosRequestConfig) {
    return getHttpClient().get<T>(url, config);
  },
  post<T>(url: string, data?: any, config?: AxiosRequestConfig) {
    return getHttpClient().post<T>(url, data, config);
  },
};

export default http;
