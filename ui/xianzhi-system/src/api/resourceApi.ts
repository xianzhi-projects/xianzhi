import http, {BASE_SERVER, type ResponseResult} from '@/api/index'

/**
 * 资源类型枚举
 */
export enum ResourceType {
  /**
   * 菜单
   */
  MENU = 'MENU',
  /**
   * 目录
   */
  CATEGORY = 'CATEGORY',
  /**
   * 按钮
   */
  BUTTON = 'BUTTON',
  /**
   * 链接
   */
  LINK = 'LINK',
}

/**
 * 资源信息入参
 */
export interface ResourceDTO {
  id: string
  resourceName: string
  resourceType: string
  resourceDesc: string
  resourceKey: string
  resourceSort: number
  parentId: string
  menuIcon: string
  menuComponent: string
  showFlag: boolean
  enableFlag: boolean
}

/**
 * 资源信息出参
 */
export interface ResourceVO {
  id: string
  resourceName: string
  resourceType: string
  resourceDesc: string
  resourceKey: string
  resourceSort: number
  parentId: string
  menuIcon: string
  menuComponent: string
  showFlag: boolean
  enableFlag: boolean
  children: ResourceVO[]
}

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
    url: BASE_SERVER + '/resource/resourceTree',
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
    url: BASE_SERVER + '/resource/deleteResource',
    method: 'POST',
    params: { id },
  })
}
