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
import io.xianzhi.system.bootstrap.service.TenantService;
import io.xianzhi.system.model.dto.TenantDTO;
import io.xianzhi.system.model.page.TenantPage;
import io.xianzhi.system.model.vo.TenantVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 租户接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/tenant")
public class TenantController {

    /**
     * 租户接口
     */
    private final TenantService tenantService;

    /**
     * 查询用户租户信息
     *
     * @return 租户信息
     */
    @GetMapping("/getUserTenantList")
    public ResponseResult<List<TenantVO>> getUserTenantList() {
        return ResponseResult.success(tenantService.getUserTenantList());
    }

    /**
     * 分页查询租户列表
     *
     * @param tenantPage 租户查询条件
     * @return 租户列表
     */
    @PreAuthorize("@xz.hasPermission('system:tenant:list')")
    @PostMapping(value = "/pageTenantList")
    public ResponseResult<ListResult<TenantVO>> pageTenantList(@RequestBody TenantPage tenantPage) {
        return ResponseResult.success(tenantService.pageTenantList(tenantPage));
    }

    /**
     * 创建租户  (幂等)
     *
     * @param tenantDTO 租户信息入参
     * @return 租户ID
     */
    @Idempotent
    @PreAuthorize("@xz.hasPermission('system:tenant:create')")
    @PostMapping(value = "/createTenant")
    public ResponseResult<String> createTenant(@RequestBody @Validated(value = CreateGroup.class) TenantDTO tenantDTO) {
        return ResponseResult.success(tenantService.createTenant(tenantDTO));
    }

    /**
     * 修改租户
     *
     * @param tenantDTO 租户信息入参
     * @return 响应信息
     */
    @PreAuthorize("@xz.hasPermission('system:tenant:update')")
    @PostMapping(value = "/updateTenant")
    public ResponseResult<Object> updateTenant(@RequestBody @Validated(value = UpdateGroup.class) TenantDTO tenantDTO) {
        tenantService.updateTenant(tenantDTO);
        return ResponseResult.success();
    }

    /**
     * 获取租户详情
     *
     * @param id 租户ID
     * @return 租户详情
     */
    @PreAuthorize("@xz.hasPermission('system:tenant:detail','system:tenant:create','system:tenant:update')")
    @GetMapping(value = "/getTenantDetails")
    public ResponseResult<TenantVO> getTenantDetails(@RequestParam(value = "id") String id) {
        return ResponseResult.success(tenantService.getTenantDetails(id));
    }

    /**
     * 删除租户
     *
     * @param id 租户ID
     * @return 响应信息
     */
    @PreAuthorize("@xz.hasPermission('system:tenant:delete')")
    @PostMapping(value = "/deletedTenant")
    public ResponseResult<Object> deletedTenant(@RequestParam(value = "id") String id) {
        tenantService.deletedTenant(id);
        return ResponseResult.success();
    }
}
