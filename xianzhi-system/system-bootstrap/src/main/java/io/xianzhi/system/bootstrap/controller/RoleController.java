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

package io.xianzhi.system.bootstrap.controller;

import io.xianzhi.common.idempotent.annotations.Idempotent;
import io.xianzhi.core.result.ListResult;
import io.xianzhi.core.result.ResponseResult;
import io.xianzhi.core.validated.CreateGroup;
import io.xianzhi.core.validated.UpdateGroup;
import io.xianzhi.system.bootstrap.service.RoleService;
import io.xianzhi.system.model.dto.RoleDTO;
import io.xianzhi.system.model.page.RolePage;
import io.xianzhi.system.model.vo.RoleVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/s/role")
public class RoleController {
    /**
     * 角色接口
     */
    private final RoleService roleService;

    /**
     * 分页查询角色列表
     *
     * @param rolePage 分页查询参数
     * @return 角色列表
     */
    @PreAuthorize("@xz.hasPermission('system:role:list')")
    @PostMapping(value = "/pageRoleList")
    public ResponseResult<ListResult<RoleVO>> pageRoleList(@RequestBody RolePage rolePage) {
        return ResponseResult.success();
    }

    /**
     * 新增角色
     *
     * @param roleDTO 角色信息
     * @return 角色ID
     */
    @Idempotent
    @PreAuthorize("@xz.hasPermission('system:role:create')")
    @PostMapping(value = "/createRole")
    public ResponseResult<String> createRole(@RequestBody @Validated(value = CreateGroup.class) RoleDTO roleDTO) {
        return ResponseResult.success();
    }

    /**
     * 更新角色
     *
     * @param roleDTO 角色信息
     * @return 响应信息
     */
    @PreAuthorize("@xz.hasPermission('system:role:update')")
    @PostMapping(value = "/updateRole")
    public ResponseResult<Object> updateRole(@RequestBody @Validated(value = UpdateGroup.class) RoleDTO roleDTO) {
        return ResponseResult.success();
    }

    /**
     * 删除角色
     *
     * @param ids 角色ID
     * @return 响应信息
     */
    @PreAuthorize("@xz.hasPermission('system:role:delete')")
    @PostMapping(value = "/deletedRole")
    public ResponseResult<Object> deletedRole(@RequestBody List<String> ids) {
        return ResponseResult.success();
    }
}
