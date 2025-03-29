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

import type {RouteRecordRaw} from "vue-router";
import {ref} from "vue";


export const menus = ref([])

function resolveRouter(routerList: RouteRecordRaw[]) {
  return undefined;
}

/**
 * 刷新菜单
 * @param routerList 菜单路由
 */
export function refreshMenu(routerList: RouteRecordRaw[]) {
  menus.value = resolveRouter(routerList)
}

