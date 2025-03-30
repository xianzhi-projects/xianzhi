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
  <el-tabs
    v-model="activeTab"
    class="demo-tabs"
    closable
    type="card"
    @tab-remove="removeTab"
    @tab-click="clickBtn"
  >
    <el-tab-pane
      v-for="item in tabsList"
      :key="item.path"
      :label="item.title"
      :name="item.path"
    >
    </el-tab-pane>
  </el-tabs>
</template>


<script lang="ts" setup>
import {onMounted, ref, watch} from 'vue'
import {useTablesStore} from '@/stores/tabsStore.ts'
import {useRoute, useRouter} from 'vue-router'
import type {TabsPaneContext} from 'element-plus'

const router =useRouter()
const route = useRoute()
//获取store
const tablesStore = useTablesStore()
//选中的选项卡数据
const activeTab = ref('')
//选项卡的数据
const tabsList = tablesStore.tabsList
//选项卡点击事件
const clickBtn =(pane:TabsPaneContext)=>{
  const {props} =pane
  router.push({path:props.name as string})
}
//删除
const removeTab = (targetName: String) => {
  const tabs = tabsList
  console.log(tabs)
  let activeName = activeTab.value
  if (activeName === targetName) {
    tabs.forEach((tab, index) => {
      if (tab.path === targetName) {
        const nextTab = tabs[index + 1] || tabs[index - 1]
        if (nextTab) {
          activeName = nextTab.path
        }
      }
    })
  }
  //重新激活选项卡
  activeTab.value = activeName
  tablesStore.tabList = tabs.filter((tab) => tab.path !== targetName)
  //跳转路由
  router.push({path:activeName})
}
const addTab = () =>{
  const tab = {
    path:route.path,
    title:route.name as string
  }
  activeTab.value = tab.path
  tablesStore.addTab(tab)
}
//监听当前路由
watch(
  ()=>route.path,
  ()=>{
    addTab()
  }
)
onMounted(()=>{
  addTab()
})
</script>


<style lang="less"  scoped>
:deep(.el-tabs__header){
  margin: 0;
}
:deep(.el-tabs__item){
  height: 26px ;
  line-height: 26px ;
  text-align: center ;
  margin: 0 3px !important;
  font-size: 12px !important;
  padding: 0 10px !important;
}

:deep(.is-active){
  background-color: #5356e0 !important;
  border-radius: 5px !important;
  color: #fff !important;
}
:deep(.is-active:hover){
  color: #fff !important;
}


</style>
