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

package io.xianzhi.system.bootstrap.service;

import io.xianzhi.system.model.dto.DepartmentDTO;
import io.xianzhi.system.model.vo.DepartmentVO;

import java.util.List;

/**
 * 部门接口
 *
 * @author Max
 * @since 1.0.0
 */
public interface DepartmentService {
    /**
     * 查询部门树结构信息
     *
     * @return 树信息
     */
    List<DepartmentVO> tree();

    /**
     * 新增部门信息
     *
     * @param departmentDTO 部门信息入参
     * @return 部门ID
     */
    String createDepartment(DepartmentDTO departmentDTO);

    /**
     * 更新部门信息
     *
     * @param departmentDTO 部门信息入参
     */
    void updateDepartment(DepartmentDTO departmentDTO);

    /**
     * 删除部门
     *
     * @param id 部门ID
     */
    void deletedDepartment(String id);
    /**
     * 获取用户有权限的部门树结构信息
     *
     * @return 用户有权限的部门树结构信息
     */
    List<DepartmentVO> getDepartmentTree();


}
