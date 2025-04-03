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
import io.xianzhi.system.bootstrap.dao.dataobj.HostCertificateDO;
import io.xianzhi.system.bootstrap.dao.mapper.HostCertificateMapper;
import io.xianzhi.system.bootstrap.service.HostCertificateService;
import io.xianzhi.system.model.dto.HostCertificateDTO;
import io.xianzhi.system.model.enums.CertTypeEnum;
import io.xianzhi.system.model.page.HostCertificatePage;
import io.xianzhi.system.model.vo.HostCertificateVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

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
        IPage<HostCertificateVO> result = hostCertificateMapper.selectHostCertificatePage(new Page<>(hostCertificatePage.getPageNo(), hostCertificatePage.getPageSize()), hostCertificatePage);
        if (ObjectUtils.isEmpty(result.getRecords())) {
            return ListResult.empty();
        }
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
        hostCertificateMapper.updateById(hostCertificateDO);

    }

    /**
     * 查询主机凭证详情
     *
     * @param id 主机凭证ID
     * @return 主机凭证详情
     */
    @Override
    public HostCertificateVO getHostCertificateById(String id) {
        HostCertificateDO hostCertificateDO = hostCertificateMapper.selectHostCertificateById(id).orElseThrow(() -> new BusinessException(CommonCode.ERROR));
        HostCertificateVO hostCertificateVO = new HostCertificateVO();
        hostCertificateVO.setId(hostCertificateDO.getId());
        hostCertificateVO.setCertName(hostCertificateDO.getCertName());
        hostCertificateVO.setCertDesc(hostCertificateDO.getCertDesc());
        hostCertificateVO.setCertType(hostCertificateDO.getCertType());
        hostCertificateVO.setUsername(hostCertificateDO.getUsername());
        hostCertificateVO.setPassword(hostCertificateDO.getPassword());
        hostCertificateVO.setPrivateKey(hostCertificateDO.getPrivateKey());
        return hostCertificateVO;
    }

    /**
     * 删除主机凭证
     *
     * @param ids 主机凭证ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteHostCertificateById(List<String> ids) {
        hostCertificateMapper.deletedHostCertificateById(ids);

    }

    /**
     * 检查凭证信息是否合法
     *
     * @param hostCertificateDTO 凭证信息入参
     * @return 凭证信息
     */
    private HostCertificateDO checkedHostCertificateDTO(HostCertificateDTO hostCertificateDTO) {
        HostCertificateDO hostCertificateDO;
        if (StringUtils.hasText(hostCertificateDTO.getId())) {
            hostCertificateDO = hostCertificateMapper.selectHostCertificateById(hostCertificateDTO.getId()).orElseThrow(() -> new BusinessException(CommonCode.ERROR));
        } else {
            hostCertificateDO = new HostCertificateDO();
        }
        if (hostCertificateDTO.getCertType().getCode().equals(CertTypeEnum.PASSWORD.getCode())) {
            if (!StringUtils.hasText(hostCertificateDTO.getUsername()) || !StringUtils.hasText(hostCertificateDTO.getPassword())) {
                throw new BusinessException(CommonCode.ERROR);
            }
        } else {
            if (!StringUtils.hasText(hostCertificateDTO.getPrivateKey())) {
                throw new BusinessException(CommonCode.ERROR);
            }
        }
        hostCertificateDO.setUsername(hostCertificateDTO.getUsername());
        hostCertificateDO.setPassword(hostCertificateDTO.getPassword());
        hostCertificateDO.setPrivateKey(hostCertificateDTO.getPrivateKey());
        hostCertificateDO.setCertDesc(hostCertificateDTO.getCertDesc());
        hostCertificateDO.setCertType(hostCertificateDTO.getCertType().getCode());
        hostCertificateDO.setCertName(hostCertificateDTO.getCertName());
        return hostCertificateDO;
    }
}
