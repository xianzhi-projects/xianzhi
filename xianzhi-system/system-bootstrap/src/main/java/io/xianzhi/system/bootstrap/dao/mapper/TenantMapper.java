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

package io.xianzhi.system.bootstrap.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.xianzhi.system.bootstrap.dao.dataobj.TenantDO;
import io.xianzhi.system.model.page.TenantPage;
import io.xianzhi.system.model.vo.TenantVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * 租户持久层
 *
 * @author Max
 * @since 1.0.0
 */
@Mapper
public interface TenantMapper extends BaseMapper<TenantDO> {

    /**
     * 根据ID查询租户信息
     *
     * @param id 租户ID
     * @return 租户信息
     */
    Optional<TenantDO> selectTenantById(@Param("id") String id);

    /**
     * 判断租户名称是否存在
     *
     * @param tenantName 租户名称
     * @param id         租户ID
     * @return true: 存在，false: 不存在
     */
    boolean existsTenantByTenantNanIdNot(@Param("tenantName") String tenantName, @Param("id") String id);

    /**
     * 判断租户名称是否存在
     *
     * @param tenantCode 租户编码
     * @return true: 存在，false: 不存在
     */
    boolean existsTenantByTenantCode(@Param("tenantCode") String tenantCode);

    /**
     * 查询租户列表
     *
     * @param tenantVOPage 分页条件
     * @param tenantPage   查询条件
     * @return 租户列表
     */
    IPage<TenantVO> pageTenantList(Page<TenantVO> tenantVOPage, @Param("tenantPage") TenantPage tenantPage);
}
