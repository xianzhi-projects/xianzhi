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

import http from '@/api/index'
import {BASE_SERVER} from "@/constants/ServerConstant.ts";
import type {ResponseResult} from "@/types/core.ts";
import type {ResourceDTO, ResourceVO} from "@/types/resource.ts";


/**
 * 获取当前用户资源信息
 */
export function getCurrentUserResource(): Promise<ResponseResult<ResourceVO[]>> {
  return http.request({
    url: BASE_SERVER + '/resource/getCurrentUserResource',
    method: 'GET',
  })
}

/**
 * 查询所有资源信息
 */
export function resourceTree(): Promise<ResponseResult<ResourceVO[]>> {
  return http.request({
    url: BASE_SERVER + '/resource/tree',
    method: 'GET',
  })
}

/**
 * 创建资源
 * @param resourceDTO 资源信息入参
 */
export function createResource(resourceDTO: ResourceDTO): Promise<ResponseResult<string>> {
  return http.request({
    url: BASE_SERVER + '/resource/createResource',
    method: 'POST',
    data: resourceDTO,
  })
}

/**
 * 修改资源
 * @param resourceDTO 资源信息入参
 */
export function updateResource(resourceDTO: ResourceDTO): Promise<ResponseResult<null>> {
  return http.request({
    url: BASE_SERVER + '/resource/updateResource',
    method: 'POST',
    data: resourceDTO,
  })
}

/**
 * 删除资源
 * @param id 资源ID
 */
export function deleteResource(id: string): Promise<ResponseResult<null>> {
  return http.request({
    url: BASE_SERVER + '/resource/deletedResource',
    method: 'POST',
    params: { id },
  })
}
