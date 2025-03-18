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

package io.xianzhi.code.bootstrap.service;

import io.xianzhi.code.model.dto.HostCertificateDTO;
import io.xianzhi.code.model.page.HostCertificatePage;
import io.xianzhi.code.model.vo.HostCertificateVO;
import io.xianzhi.core.result.ListResult;

import java.util.List;

/**
 * 主机凭证接口
 *
 * @author Max
 * @since 1.0.0
 */
public interface HostCertificateService {
    /**
     * 分页查询主机凭证
     *
     * @param hostCertificatePage 主机凭证接口
     * @return 主机凭证
     */
    ListResult<HostCertificateVO> pageHostCertificate(HostCertificatePage hostCertificatePage);
    /**
     * 创建主机凭证
     *
     * @param hostCertificateDTO 主机凭证入参
     * @return 主机凭证ID
     */
    String createHostCertificate(HostCertificateDTO hostCertificateDTO);
    /**
     * 更新主机凭证
     *
     * @param hostCertificateDTO 主机凭证入参
     */
    void updateHostCertificate(HostCertificateDTO hostCertificateDTO);

    /**
     * 查询主机凭证详情
     *
     * @param id 主机凭证ID
     * @return 主机凭证详情
     */
    HostCertificateVO getHostCertificateById(String id);

    /**
     * 删除主机凭证
     *
     * @param ids 主机凭证ID
     */
    void deleteHostCertificateById(List<String> ids);
}
