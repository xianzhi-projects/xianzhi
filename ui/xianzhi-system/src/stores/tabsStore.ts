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


import {defineStore} from "pinia";
import {computed, ref} from "vue";

export type Tab = {
  title: string;
  path: string;
}
export type TabState = {
  tabList: Tab[]
}
export const useTablesStore = defineStore('tables', () => {
  const tabList = ref<Tab[]>([])
  const tabsList = computed(()=>{
    return tabList.value
  })


  function addTab(tab: Tab) {
    //判断数据是否在选项卡里面
    if (tabList.value.some(item => item.path === tab.path)) return;
    tabList.value.push(tab)
  }


  return {
    tabList,
    tabsList,
    addTab
  }

})


