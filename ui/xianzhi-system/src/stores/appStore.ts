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
// src/stores/app.ts
// src/stores/app.ts
import {defineStore} from 'pinia';
import {ref, watch} from 'vue';

// 定义应用状态接口
interface AppState {
    theme: 'light' | 'dark'; // 主题：明亮或暗色
    primaryColor: string; // 主色调
}

export const useAppStore = defineStore('app', () => {
    // 状态
    const theme = ref<AppState['theme']>('light'); // 默认明亮主题
    const primaryColor = ref<AppState['primaryColor']>('#409EFF'); // 默认主色调（Element Plus 默认蓝色）

    // 切换主题
    const toggleTheme = () => {
        theme.value = theme.value === 'light' ? 'dark' : 'light';
    };

    // 设置主色调
    const setPrimaryColor = (color: string) => {
        primaryColor.value = color;
    };

    // 监听主题变化，动态切换暗色模式
    watch(theme, (newTheme) => {
        document.documentElement.setAttribute('data-theme', newTheme);
        localStorage.setItem('theme', newTheme);
    });

    // 监听主色调变化，动态更新 CSS 变量
    watch(primaryColor, (newColor) => {
        document.documentElement.style.setProperty('--el-color-primary', newColor);
        // 动态生成主色调的衍生颜色（Element Plus 使用 SCSS mix 生成衍生色，这里简化处理）
        for (let i = 1; i <= 9; i++) {
            const lightenedColor = lightenColor(newColor, i * 0.1); // 模拟 light-1 到 light-9
            document.documentElement.style.setProperty(`--el-color-primary-light-${i}`, lightenedColor);
        }
        localStorage.setItem('primaryColor', newColor);
    });

    // 简化的颜色变亮函数（实际项目中可能需要更复杂的颜色处理）
    const lightenColor = (color: string, amount: number): string => {
        let r = parseInt(color.slice(1, 3), 16);
        let g = parseInt(color.slice(3, 5), 16);
        let b = parseInt(color.slice(5, 7), 16);
        r = Math.min(255, Math.floor(r + (255 - r) * amount));
        g = Math.min(255, Math.floor(g + (255 - g) * amount));
        b = Math.min(255, Math.floor(b + (255 - b) * amount));
        return `#${r.toString(16).padStart(2, '0')}${g.toString(16).padStart(2, '0')}${b.toString(16).padStart(2, '0')}`;
    };

    // 初始化时从本地存储恢复状态
    const init = () => {
        const savedTheme = localStorage.getItem('theme') as AppState['theme'];
        const savedPrimaryColor = localStorage.getItem('primaryColor') as AppState['primaryColor'];
        if (savedTheme) theme.value = savedTheme;
        if (savedPrimaryColor) primaryColor.value = savedPrimaryColor;
    };

    // 立即执行初始化
    init();

    return { theme, primaryColor, toggleTheme, setPrimaryColor };
});
