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
import io.xianzhi.system.bootstrap.dao.dataobj.HostCertificateDO;
import io.xianzhi.system.model.page.HostCertificatePage;
import io.xianzhi.system.model.vo.HostCertificateVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * 主机凭证持久层
 *
 * @author Max
 * @since 1.0.0
 */
@Mapper
public interface HostCertificateMapper extends BaseMapper<HostCertificateDO> {
    /**
     * 根据id查询主机凭证
     *
     * @param id 凭证ID
     * @return 凭证信息
     */
    Optional<HostCertificateDO> selectHostCertificateById(@Param("id") String id);

    /**
     * 判断凭证名称是否存在
     *
     * @param certName 凭证名称
     * @param id       凭证ID
     * @return 是否存在
     */
    boolean existsHostCertificateByCertNameAndIdNot(@Param("certName") String certName, @Param("id") String id);

    /**
     * 根据id删除主机凭证
     *
     * @param ids 凭证ID
     */
    void deletedHostCertificateById(@Param("ids") List<String> ids);

    /**
     * 分页查询主机凭证
     * @param hostCertificateVOPage 分页条件
     * @param hostCertificatePage 查询条件
     * @return 主机凭证
     */
    IPage<HostCertificateVO> selectHostCertificatePage(Page<HostCertificateVO> hostCertificateVOPage, @Param("hostCertificatePage") HostCertificatePage hostCertificatePage);
}
