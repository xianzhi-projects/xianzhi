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

import io.xianzhi.core.result.ListResult;
import io.xianzhi.system.model.dto.RoleDTO;
import io.xianzhi.system.model.page.RolePage;
import io.xianzhi.system.model.vo.RoleVO;

import java.util.List;

/**
 * 角色接口
 *
 * @author Max
 * @since 1.0.0
 */
public interface RoleService {
    /**
     * 分页查询角色列表
     *
     * @param rolePage 分页查询参数
     * @return 角色列表
     */
    ListResult<RoleVO> pageRoleList(RolePage rolePage);

    /**
     * 新增角色
     *
     * @param roleDTO 角色信息
     * @return 角色ID
     */
    String createRole(RoleDTO roleDTO);

    /**
     * 更新角色
     *
     * @param roleDTO 角色信息
     */
    void updateRole(RoleDTO roleDTO);

    /**
     * 删除角色
     *
     * @param ids 角色ID
     */
    void deletedRole(List<String> ids);
}
