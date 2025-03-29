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


/**
 * 响应信息
 */
export interface ResponseResult<T> {
  /**
   * 提示信息
   */
  message: string
  /**
   * 状态码
   */
  code: string
  /**
   * 数据
   */
  data: T | null
  /**
   * traceId
   */
  traceId: string
}

/**
 * 查询列表返回的数据
 */
export interface ListResult<T> {
  /**
   * 总数
   */
  total: number
  /**
   * 查询的数据
   */
  list: T[]
}

