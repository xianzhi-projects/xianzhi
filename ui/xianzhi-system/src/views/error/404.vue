<!-- src/views/NotFound.vue -->
<template>
  <div class="not-found-container">
    <div class="content">
      <h1 class="error-code">
        <span :style="{ animationDelay: '0s' }" class="digit">4</span>
        <span :style="{ animationDelay: '0.2s' }" class="digit">0</span>
        <span :style="{ animationDelay: '0.4s' }" class="digit">4</span>
      </h1>
      <p class="message">Oops! 页面走丢了...</p>
      <p class="sub-message">看起来你迷路了，别担心，我们带你回家！</p>
      <el-button class="home-button" round type="primary" @click="goHome">
        返回首页
      </el-button>
    </div>
    <div class="background-shapes">
      <div class="shape shape1"></div>
      <div class="shape shape2"></div>
      <div class="shape shape3"></div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {useRouter} from 'vue-router';

// 获取路由实例
const router = useRouter();

// 返回首页
const goHome = () => {
  router.push('/analytics');
};
</script>

<style lang="less" scoped>
.not-found-container {
  height: 100vh;
  width: 100vw;
  display: flex;
  justify-content: center;
  align-items: center;
  background: linear-gradient(135deg, #6e8efb, #a777e3);
  position: relative;
  overflow: hidden;
}

.content {
  text-align: center;
  color: #fff;
  z-index: 1;
}

.error-code {
  font-size: 12rem;
  font-weight: 900;
  line-height: 1;
  margin: 0;
  display: flex;
  justify-content: center;
  gap: 1rem;
}

.digit {
  display: inline-block;
  animation: float 3s ease-in-out infinite;
}

.message {
  font-size: 2rem;
  font-weight: 500;
  margin: 20px 0 10px;
}

.sub-message {
  font-size: 1.2rem;
  opacity: 0.8;
  margin-bottom: 30px;
}

.home-button {
  padding: 12px 30px;
  font-size: 1.1rem;
  background: #fff;
  color: #6e8efb;
  border: none;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  &:hover {
    transform: translateY(-3px);
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
  }
}

.background-shapes {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  pointer-events: none;
}

.shape {
  position: absolute;
  border-radius: 50%;
  background: rgba(255, 255, 255, 0.1);
  animation: drift 15s infinite linear;
}

.shape1 {
  width: 200px;
  height: 200px;
  top: 10%;
  left: 15%;
  animation-delay: 0s;
}

.shape2 {
  width: 150px;
  height: 150px;
  top: 60%;
  left: 70%;
  animation-delay: 5s;
}

.shape3 {
  width: 300px;
  height: 300px;
  top: 40%;
  left: 30%;
  animation-delay: 10s;
}

@keyframes float {
  0%,
  100% {
    transform: translateY(0);
  }
  50% {
    transform: translateY(-20px);
  }
}

@keyframes drift {
  0% {
    transform: translate(0, 0);
  }
  50% {
    transform: translate(50px, 50px);
  }
  100% {
    transform: translate(0, 0);
  }
}
</style>
