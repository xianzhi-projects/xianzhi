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
import io.xianzhi.system.model.dto.TenantDTO;
import io.xianzhi.system.model.page.TenantPage;
import io.xianzhi.system.model.vo.TenantVO;

import java.util.List;

/**
 * 租户接口
 *
 * @author Max
 * @since 1.0.0
 */
public interface TenantService {
    /**
     * 查询用户租户信息
     *
     * @return 租户信息
     */
    List<TenantVO> getUserTenantList();

    /**
     * 分页查询租户列表
     *
     * @param tenantPage 租户查询条件
     * @return 租户列表
     */
    ListResult<TenantVO> pageTenantList(TenantPage tenantPage);

    /**
     * 创建租户
     *
     * @param tenantDTO 租户信息入参
     * @return 租户ID
     */
    String createTenant(TenantDTO tenantDTO);

    /**
     * 修改租户
     *
     * @param tenantDTO 租户信息入参
     */
    void updateTenant(TenantDTO tenantDTO);
    /**
     * 获取租户详情
     *
     * @param id 租户ID
     * @return 租户详情
     */
    TenantVO getTenantDetails(String id);
    /**
     * 删除租户
     *
     * @param id 租户ID
     */
    void deletedTenant(String id);
}
