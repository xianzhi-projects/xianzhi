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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.exception.BusinessException;
import io.xianzhi.core.result.ListResult;
import io.xianzhi.system.bootstrap.dao.dataobj.TenantDO;
import io.xianzhi.system.bootstrap.dao.mapper.FileMapper;
import io.xianzhi.system.bootstrap.dao.mapper.TenantMapper;
import io.xianzhi.system.bootstrap.dao.mapper.UserMapper;
import io.xianzhi.system.bootstrap.service.TenantService;
import io.xianzhi.system.model.dto.TenantDTO;
import io.xianzhi.system.model.page.TenantPage;
import io.xianzhi.system.model.vo.TenantVO;
import io.xianzhi.system.security.context.UserContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 租户接口实习啊你
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {

    /**
     * 租户接口
     */
    private final TenantMapper tenantMapper;

    /**
     * 用户信息持久层
     */
    private final UserMapper userMapper;

    /**
     * 文件信息持久层
     */
    private final FileMapper fileMapper;

    /**
     * 查询用户租户信息
     *
     * @return 租户信息
     */
    @Override
    public List<TenantVO> getUserTenantList() {
        String userId = UserContextHolder.getCurrentUserId();
        return List.of();
    }

    /**
     * 分页查询租户列表
     *
     * @param tenantPage 租户查询条件
     * @return 租户列表
     */
    @Override
    public ListResult<TenantVO> pageTenantList(TenantPage tenantPage) {
        IPage<TenantVO> result = tenantMapper.pageTenantList(new Page<>(tenantPage.getPageNo(), tenantPage.getPageSize()), tenantPage);
        if (ObjectUtils.isEmpty(result.getRecords())) {
            return ListResult.empty();
        }
        return null;
    }

    /**
     * 创建租户
     *
     * @param tenantDTO 租户信息入参
     * @return 租户ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createTenant(TenantDTO tenantDTO) {
        TenantDO tenantDO = checkedTenantDTO(tenantDTO);
        tenantMapper.insert(tenantDO);
        return tenantDO.getId();
    }

    /**
     * 修改租户
     *
     * @param tenantDTO 租户信息入参
     */
    @Override
    public void updateTenant(TenantDTO tenantDTO) {

    }

    /**
     * 获取租户详情
     *
     * @param id 租户ID
     * @return 租户详情
     */
    @Override
    public TenantVO getTenantDetails(String id) {
        return null;
    }

    /**
     * 删除租户
     *
     * @param id 租户ID
     */
    @Override
    public void deletedTenant(String id) {

    }

    /**
     * 检查租户信息入参
     *
     * @param tenantDTO 租户信息入参
     * @return 租户信息
     */
    private TenantDO checkedTenantDTO(TenantDTO tenantDTO) {
        TenantDO tenant;
        if (StringUtils.hasText(tenantDTO.getId())) {
            tenant = tenantMapper.selectTenantById(tenantDTO.getId()).orElseThrow(() -> new BusinessException(CommonCode.DATA_NOT_EXISTS.code(), "sys.tenant.not.exists"));
        } else {
            tenant = new TenantDO();
            if (tenantMapper.existsTenantByTenantCode(tenantDTO.getTenantCode())) {
                throw new BusinessException(CommonCode.DATA_EXISTS.code(), "sys.tenant.code.exists");
            }
            tenant.setTenantCode(tenantDTO.getTenantCode());
        }
        if (tenantMapper.existsTenantByTenantNanIdNot(tenantDTO.getTenantName(), tenantDTO.getId())) {
            throw new BusinessException(CommonCode.DATA_EXISTS.code(), "sys.tenant.name.exists");
        }
        userMapper.selectUserById(tenantDTO.getId()).orElseThrow(() -> new BusinessException(CommonCode.DATA_NOT_EXISTS.code(), "sys.tenant.owner.not.exists"));
        tenant.setTenantName(tenantDTO.getTenantName());
        tenant.setTenantOwner(tenantDTO.getTenantOwner());
        tenant.setTenantDesc(tenantDTO.getTenantDesc());
        return tenant;
    }
}
