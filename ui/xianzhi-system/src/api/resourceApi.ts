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
