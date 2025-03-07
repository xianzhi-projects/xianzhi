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

import io.xianzhi.core.result.ListResult;
import io.xianzhi.system.bootstrap.dao.mapper.RoleMapper;
import io.xianzhi.system.bootstrap.service.RoleService;
import io.xianzhi.system.model.dto.RoleDTO;
import io.xianzhi.system.model.page.RolePage;
import io.xianzhi.system.model.vo.RoleVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色接口实现
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    /**
     * 角色持久层
     */
    private final RoleMapper roleMapper;

    /**
     * 分页查询角色列表
     *
     * @param rolePage 分页查询参数
     * @return 角色列表
     */
    @Override
    public ListResult<RoleVO> pageRoleList(RolePage rolePage) {
        return null;
    }

    /**
     * 新增角色
     *
     * @param roleDTO 角色信息
     * @return 角色ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createRole(RoleDTO roleDTO) {
        return "";
    }

    /**
     * 更新角色
     *
     * @param roleDTO 角色信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(RoleDTO roleDTO) {

    }

    /**
     * 删除角色
     *
     * @param ids 角色ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletedRole(List<String> ids) {

    }
}
