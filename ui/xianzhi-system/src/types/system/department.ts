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
 * 部门信息出参
 */
export interface DepartmentVO {
  /**
   * 部门ID
   */
  id: string
  /**
   * 部门名称
   */
  departmentName: string
  /**
   * 部门描述
   */
  departmentDesc: string
  /**
   * 部门排序
   */
  departmentSort: number
  /**
   * 子集部门
   */
  children: DepartmentVO[]


}

export interface DepartmentDTO {

}

