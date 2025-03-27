<!-- src/App.vue -->
<template>
  <div class="app-container">
    <!-- 顶部导航栏 -->
    <el-header class="app-header">
      <div class="header-left">
        <h1>欢迎使用</h1>
      </div>
      <div class="header-right">
        <!-- 主色调选择 -->
        <el-color-picker v-model="appStore.primaryColor" show-alpha />

        <!-- 主题切换 -->
        <el-button @click="appStore.toggleTheme">
          <el-icon>
            <Sunny v-if="appStore.theme === 'light'" />
            <Moon v-else />
          </el-icon>
          {{ appStore.theme === 'light' ? '明亮主题' : '暗色主题' }}
        </el-button>
      </div>
    </el-header>

    <!-- 主内容区域 -->
    <el-main>
      <router-view />
    </el-main>
  </div>
</template>

<script lang="ts" setup>
import {useAppStore} from '@/stores/appStore.ts'; // 引入 Pinia store
import {ElButton, ElColorPicker, ElHeader, ElIcon, ElMain} from 'element-plus';
import {Moon, Sunny} from '@element-plus/icons-vue';

// 获取 Pinia store
const appStore = useAppStore();
</script>

<style lang="less" scoped>
// 定义变量
@header-height: 60px;
@text-color: #333;
@text-color-secondary: #666;
@background-color: #f0f2f5;

// 应用容器
.app-container {
  min-height: 100vh;
  background: @background-color;
  transition: background-color 0.3s;

  // 暗色主题样式
  &[data-theme='dark'] {
    background: #1f2a44;

    .app-header {
      background: #141414;
      border-bottom: 1px solid #303030;

      h1,
      .el-button {
        color: #fff;
      }
    }

    .el-main {
      color: #fff;
    }
  }
}

// 顶部导航栏
.app-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: @header-height;
  background: #fff;
  border-bottom: 1px solid #e8e8e8;
  padding: 0 20px;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);

  .header-left {
    h1 {
      font-size: 20px;
      color: @text-color;
      margin: 0;
    }
  }

  .header-right {
    display: flex;
    gap: 20px;
    align-items: center;

    .el-button {
      color: @text-color-secondary;
      font-size: 14px;

      &:hover {
        color: #409eff;
      }
    }

    // 调整颜色选择器的样式
    .el-color-picker {
      :deep(.el-color-picker__trigger) {
        border: none;
        padding: 0;
        width: 24px;
        height: 24px;
      }
    }
  }
}

// 主内容区域
.el-main {
  padding: 20px;
  color: @text-color;
}
</style>