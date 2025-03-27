/*
 * Copyright 2025 XianZhi Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import {defineConfig, loadEnv} from 'vite';
import vue from '@vitejs/plugin-vue';
import Components from 'unplugin-vue-components/vite';
import AutoImport from 'unplugin-auto-import/vite';
import path from 'path';
import {ElementPlusResolver} from 'unplugin-vue-components/resolvers';

// https://vitejs.dev/config/
export default defineConfig(({ mode }) => {
  // 加载环境变量
  const env = loadEnv(mode, process.cwd(), '');

  return {
    plugins: [
      vue(),
      AutoImport({
        resolvers: [ElementPlusResolver()],
      }),
      Components({
        resolvers: [ElementPlusResolver()],
      }),
    ],

    server: {
      port: 3000,
      open: true,
      host: '0.0.0.0',
      proxy: {
        '/api': {
          target: env.VITE_API_URL, // 使用 loadEnv 加载的环境变量
          changeOrigin: true, // 确保 Host 头正确
          ws: true,
          rewrite: (path) => path.replace(/^\/api/, ''),
          // 添加日志以调试代理
          configure: (proxy) => {
            proxy.on('proxyReq', (proxyReq, req) => {
              console.log(`Proxying ${req.method} ${req.url} to ${env.VITE_API_URL}`);
              // 使用 proxyReq 修改请求头
              proxyReq.setHeader('X-Custom-Header', 'Custom-Value');
            });
            proxy.on('error', (err, _req, res) => {
              console.error('Proxy error:', err);
              res.statusCode = 500; // 设置状态码
              res.setHeader('Content-Type', 'text/plain'); // 设置响应头
              res.end('Proxy error'); // 发送响应内容
            });
          },
        },
      },
    },

    resolve: {
      alias: {
        '@': path.resolve(__dirname, './src'), // 修正路径解析
      },
    },
  };
});