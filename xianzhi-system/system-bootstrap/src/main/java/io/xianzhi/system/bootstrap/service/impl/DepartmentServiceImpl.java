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

package io.xianzhi.system.bootstrap.service.impl;

import io.xianzhi.system.bootstrap.dao.mapper.DepartmentMapper;
import io.xianzhi.system.bootstrap.service.DepartmentService;
import io.xianzhi.system.model.dto.DepartmentDTO;
import io.xianzhi.system.model.vo.DepartmentVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门接口实现
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    /**
     * 部门信息持久层
     */
    private final DepartmentMapper departmentMapper;

    /**
     * 查询部门树结构信息
     *
     * @return 树信息
     */
    @Override
    public List<DepartmentVO> tree() {
        return List.of();
    }

    /**
     * 新增部门信息
     *
     * @param departmentDTO 部门信息入参
     * @return 部门ID
     */
    @Override
    public String createDepartment(DepartmentDTO departmentDTO) {
        return "";
    }

    /**
     * 更新部门信息
     *
     * @param departmentDTO 部门信息入参
     */
    @Override
    public void updateDepartment(DepartmentDTO departmentDTO) {

    }

    /**
     * 删除部门
     *
     * @param id 部门ID
     */
    @Override
    public void deletedDepartment(String id) {

    }
}
