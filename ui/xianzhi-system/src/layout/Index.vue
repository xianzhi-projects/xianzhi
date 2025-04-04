<!--
  -  Copyright 2025 XianZhi Group .
  -
  -  Licensed under the Apache License, Version 2.0 (the "License");
  -  you may not use this file except in compliance with the License.
  -  You may obtain a copy of the License at
  -
  -      http://www.apache.org/licenses/LICENSE-2.0
  -
  -  Unless required by applicable law or agreed to in writing, software
  -  distributed under the License is distributed on an "AS IS" BASIS,
  -  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  -  See the License for the specific language governing permissions and
  -  limitations under the License.
  -->

<template>
  <div class="">
    <el-container>
      <Aside/>
      <el-container>
        <el-header>
          <button @click="toggleDark()">
            <span class="ml-2">{{ isDark ? 'Dark' : 'Light' }}</span>
          </button>
          <Header/>
        </el-header>
        <Tables/>
        <el-main>
          <transition name="el-zoom-in-left">
            <RouterView/>
          </transition>

        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script lang="ts" setup>
import {useToggle} from '@vueuse/shared'
import {useDark} from '@vueuse/core'
import Aside from "@/layout/components/Aside.vue";
import Header from "@/layout/components/Header.vue";
import Tables from "@/layout/components/Tables.vue";
import {onBeforeMount} from "vue";
import {refreshUserInfo} from "@/layout/index.ts";

const isDark = useDark()
const toggleDark = useToggle(isDark)

onBeforeMount(() => {
  refreshUserInfo()
})
</script>
<style lang="less" scoped>
.el-container {
  height: 100vh;
  min-height: 100vh;
  .el-header {
    height: 48px;
    line-height: 48px;
    text-align: right;
    padding: 0 20px;
  }
  .el-main{
    padding: 10px;
  }
}


</style>
