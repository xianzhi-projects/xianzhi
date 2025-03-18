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

package io.xianzhi.code.bootstrap.service.impl;

import io.xianzhi.code.bootstrap.dao.dataobj.HostCertificateDO;
import io.xianzhi.code.bootstrap.dao.mapper.HostCertificateMapper;
import io.xianzhi.code.bootstrap.service.HostCertificateService;
import io.xianzhi.code.model.dto.HostCertificateDTO;
import io.xianzhi.code.model.page.HostCertificatePage;
import io.xianzhi.code.model.vo.HostCertificateVO;
import io.xianzhi.core.exception.BusinessException;
import io.xianzhi.core.result.ListResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 主机凭证接口实现
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class HostCertificateImpl implements HostCertificateService {
    /**
     * 主机凭证持久层
     */
    private final HostCertificateMapper hostCertificateMapper;


    /**
     * 分页查询主机凭证
     *
     * @param hostCertificatePage 主机凭证接口
     * @return 主机凭证
     */
    @Override
    public ListResult<HostCertificateVO> pageHostCertificate(HostCertificatePage hostCertificatePage) {
        return null;
    }

    /**
     * 创建主机凭证
     *
     * @param hostCertificateDTO 主机凭证入参
     * @return 主机凭证ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createHostCertificate(HostCertificateDTO hostCertificateDTO) {
        HostCertificateDO hostCertificateDO = checkedHostCertificateDTO(hostCertificateDTO);
        hostCertificateMapper.insert(hostCertificateDO);
        return hostCertificateDO.getId();
    }

    /**
     * 更新主机凭证
     *
     * @param hostCertificateDTO 主机凭证入参
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateHostCertificate(HostCertificateDTO hostCertificateDTO) {
        HostCertificateDO hostCertificateDO = checkedHostCertificateDTO(hostCertificateDTO);

    }

    /**
     * 查询主机凭证详情
     *
     * @param id 主机凭证ID
     * @return 主机凭证详情
     */
    @Override
    public HostCertificateVO getHostCertificateById(String id) {
        return null;
    }

    /**
     * 删除主机凭证
     *
     * @param id 主机凭证ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteHostCertificateById(String id) {

    }


    private HostCertificateDO checkedHostCertificateDTO(HostCertificateDTO hostCertificateDTO) {
        HostCertificateDO hostCertificateDO;
        if (StringUtils.hasText(hostCertificateDTO.getId())) {
            hostCertificateDO = hostCertificateMapper.selectHostCertificateById(hostCertificateDTO.getId()).orElseThrow(() -> new BusinessException("主机凭证不存在"));
        } else {
            hostCertificateDO = new HostCertificateDO();
        }
        return hostCertificateDO;
    }
}
