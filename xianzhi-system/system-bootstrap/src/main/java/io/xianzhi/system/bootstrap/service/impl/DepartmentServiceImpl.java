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

import io.xianzhi.system.bootstrap.dao.dataobj.DepartmentDO;
import io.xianzhi.system.bootstrap.dao.mapper.DepartmentMapper;
import io.xianzhi.system.bootstrap.service.DepartmentService;
import io.xianzhi.system.model.dto.DepartmentDTO;
import io.xianzhi.system.model.vo.DepartmentVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
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
        List<DepartmentVO> departments = departmentMapper.selectAllDepartment();
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

    /**
     * 转换部门信息树
     *
     * @param departments 部门信息
     * @return 部门信息出参
     */
    public List<DepartmentVO> convert(List<DepartmentDO> departments) {
        if (ObjectUtils.isEmpty(departments)) {
            return Collections.emptyList();
        }
        return departments.stream().filter(item -> null == item.getParentId() || "-1".equals(item.getParentId()))
                .map(item -> {
                    DepartmentVO vo = convert(item);
                    vo.setChildren(getChildren(item.getId(), departments));
                    return vo;
                }).toList();
    }

    /**
     * 获取子集部门
     *
     * @param parentId    父级ID
     * @param departments 部门信息
     * @return 部门信息出参
     */
    private List<DepartmentVO> getChildren(String parentId, List<DepartmentDO> departments) {
        return departments.stream().filter(item -> parentId.equals(item.getParentId()))
                .map(item -> {
                    DepartmentVO vo = convert(item);
                    vo.setChildren(getChildren(item.getId(), departments));
                    return vo;
                }).toList();
    }

    /**
     * 转换换部门信息出参
     *
     * @param department 部门信息
     * @return 部门信息出参
     */
    public DepartmentVO convert(DepartmentDO department) {
        DepartmentVO vo = new DepartmentVO();
//        vo.setId(department.getId());
//        vo.setDepartmentName(department.getDepartmentName());
//        vo.setDepartmentDesc(department.getDepartmentDesc());
//        vo.setDepartmentOwner(department.getDepartmentOwner());
//        vo.setDepartmentEmail(department.getDepartmentEmail());
//        vo.setDepartmentPhone(department.getDepartmentPhone());
        return vo;
    }
}
