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

import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.exception.BusinessException;
import io.xianzhi.system.bootstrap.business.UserBusiness;
import io.xianzhi.system.bootstrap.dao.dataobj.DepartmentDO;
import io.xianzhi.system.bootstrap.dao.mapper.DepartmentMapper;
import io.xianzhi.system.bootstrap.dao.mapper.UserMapper;
import io.xianzhi.system.bootstrap.service.DepartmentService;
import io.xianzhi.system.model.dto.DepartmentDTO;
import io.xianzhi.system.model.vo.DepartmentVO;
import io.xianzhi.system.model.vo.UserVO;
import io.xianzhi.system.security.context.UserContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
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
     * 用户信息持久层
     */
    private final UserMapper userMapper;

    /**
     * 用户业务类
     */
    private final UserBusiness userBusiness;

    /**
     * 查询部门树结构信息
     *
     * @return 树信息
     */
    @Override
    public List<DepartmentVO> tree() {
        List<DepartmentDO> departments = departmentMapper.selectAllDepartment();
        if (ObjectUtils.isEmpty(departments)) {
            return List.of();
        }
        List<String> ownerIds = departments.stream().map(DepartmentDO::getDepartmentOwner).distinct().toList();
        List<UserVO> owners = userMapper.selectSimpleUserList(ownerIds);
        return departments.stream().filter(item -> null == item.getParentId() || item.getParentId().equals("-1"))
                .map(item -> convert(item, owners, departments)).toList();
    }


    /**
     * 新增部门信息
     *
     * @param departmentDTO 部门信息入参
     * @return 部门ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createDepartment(DepartmentDTO departmentDTO) {
        DepartmentDO departmentDO = checkedDepartmentDTO(departmentDTO);
        departmentMapper.insert(departmentDO);
        return departmentDO.getId();
    }

    /**
     * 更新部门信息
     *
     * @param departmentDTO 部门信息入参
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDepartment(DepartmentDTO departmentDTO) {
        DepartmentDO departmentDO = checkedDepartmentDTO(departmentDTO);
        departmentMapper.updateById(departmentDO);

    }

    /**
     * 删除部门
     *
     * @param id 部门ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletedDepartment(String id) {
        if (userMapper.existsByDepartmentId(id)) {
            throw new BusinessException(CommonCode.ERROR);
        }
        if (departmentMapper.existsByParentId(id)) {
            throw new BusinessException(CommonCode.ERROR);
        }
        departmentMapper.deletedDepartmentById(id);
    }

    /**
     * 获取用户有权限的部门树结构信息
     *
     * @return 用户有权限的部门树结构信息
     */
    @Override
    public List<DepartmentVO> getDepartmentTree() {
        String userId = UserContextHolder.getCurrentUserId();
        String departmentId = userMapper.selectUserById(userId).orElseThrow(() -> new BusinessException(CommonCode.DATA_NOT_EXISTS.code(), "sys.user.not.exists")).getDepartmentId();
        DepartmentDO department = departmentMapper.selectDepartmentById(departmentId).orElseThrow(() -> new BusinessException(CommonCode.DATA_NOT_EXISTS.code(), "sys.department.not.exists"));
        List<String> departmentIds = departmentMapper.selectSubDepartmentIdById(departmentId);
        departmentIds.add(departmentId);
        String departmentIdBreadCrumb = department.getDepartmentIdBreadCrumb();
        if (StringUtils.hasText(departmentIdBreadCrumb)) {
            List<String> parentIds = Arrays.stream(departmentIdBreadCrumb.split("/")).filter(StringUtils::hasText).toList();
            if (!ObjectUtils.isEmpty(parentIds)) {
                departmentIds.addAll(parentIds);
            }
        }
        List<DepartmentDO> departments = departmentMapper.selectSimpleDepartmentByIds(departmentIds);
        if (ObjectUtils.isEmpty(departments)) {
            return new ArrayList<>();
        }
        return departments.stream().filter(item -> null == item.getParentId() || item.getParentId().equals("-1"))
                .map(item -> convert(item, null, departments)).toList();
    }


    /**
     * 检查部门信息入参
     *
     * @param departmentDTO 部门信息入参
     * @return 部门信息
     */
    private DepartmentDO checkedDepartmentDTO(DepartmentDTO departmentDTO) {
        DepartmentDO department;
        if (StringUtils.hasText(departmentDTO.getId())) {
            department = departmentMapper.selectDepartmentById(departmentDTO.getId()).orElseThrow(() -> new BusinessException(CommonCode.DATA_NOT_EXISTS.code(), "sys.department.not.exists"));
        } else {
            department = new DepartmentDO();
        }
        // 检查父级部门信息
        if (StringUtils.hasText(departmentDTO.getParentId())) {
            if (StringUtils.hasText(departmentDTO.getId()) && departmentDTO.getId().equals(departmentDTO.getParentId())) {
                throw new BusinessException(CommonCode.ERROR);
            }
            DepartmentDO parent = departmentMapper.selectDepartmentById(departmentDTO.getParentId()).orElseThrow(() -> new BusinessException(CommonCode.DATA_NOT_EXISTS.code(), "sys.department.parent.not.exists"));
            department.setParentId(parent.getId());
            department.setDepartmentIdBreadCrumb(parent.getDepartmentIdBreadCrumb() + "/" + parent.getId());
        }
        // 检查部门名称是否存在
        if (departmentMapper.existsDepartmentByNameAndIdNot(departmentDTO.getDepartmentName(), department.getId())) {
            throw new BusinessException(CommonCode.DATA_EXISTS.code(), "sys.department.name.exists");
        }
        // 检查部门邮箱是否存在
        if (StringUtils.hasText(departmentDTO.getDepartmentEmail())) {
            if (departmentMapper.existsDepartmentByEmailAndIdNot(departmentDTO.getDepartmentEmail(), department.getId())) {
                throw new BusinessException(CommonCode.DATA_EXISTS.code(), "sys.department.email.exists");
            }
        }
        // 检查部门电话是否存在
        if (StringUtils.hasText(departmentDTO.getDepartmentPhone())) {
            if (departmentMapper.existsDepartmentByPhoneAndIdNot(departmentDTO.getDepartmentPhone(), department.getId())) {
                throw new BusinessException(CommonCode.DATA_EXISTS.code(), "sys.department.phone.exists");
            }
        }
        // 检查部门负责人
        userMapper.selectUserById(departmentDTO.getDepartmentOwner()).orElseThrow(() -> new BusinessException(CommonCode.DATA_NOT_EXISTS.code(), "sys.department.owner.not.exists"));
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setDepartmentDesc(departmentDTO.getDepartmentDesc());
        department.setDepartmentOwner(departmentDTO.getDepartmentOwner());
        department.setDepartmentEmail(departmentDTO.getDepartmentEmail());
        department.setDepartmentPhone(departmentDTO.getDepartmentPhone());
        return department;

    }

    /**
     * 获取子集部门信息
     *
     * @param id          父级部门ID
     * @param departments 部门信息
     * @param owners      负责人信息
     * @return 子集部门信息
     */
    private List<DepartmentVO> getChildren(String id, List<DepartmentDO> departments, List<UserVO> owners) {
        return departments.stream().filter(item -> null != item.getParentId() && item.getParentId().equals(id))
                .map(item -> convert(item, owners, departments)).toList();
    }


    /**
     * 转换部门信息
     *
     * @param item        部门信息
     * @param owners      负责人信息
     * @param departments 部门信息
     * @return 部门信息
     */
    private DepartmentVO convert(DepartmentDO item, List<UserVO> owners, List<DepartmentDO> departments) {
        DepartmentVO vo = convert(item);
        if (!ObjectUtils.isEmpty(owners)) {
//            owners.stream().filter(user -> user.getId().equals(item.getDepartmentOwner())).findFirst().ifPresent(vo::setDepartmentOwner);
        }
        vo.setChildren(getChildren(item.getId(), departments, owners));
        return vo;
    }


    public DepartmentVO convert(DepartmentDO departmentDO) {
        DepartmentVO vo = new DepartmentVO();
        vo.setId(departmentDO.getId());
        vo.setDepartmentName(departmentDO.getDepartmentName());
        vo.setDepartmentDesc(departmentDO.getDepartmentDesc());
        vo.setDepartmentEmail(departmentDO.getDepartmentEmail());
        vo.setDepartmentPhone(departmentDO.getDepartmentPhone());
        return vo;

    }

}
