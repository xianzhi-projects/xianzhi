

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
import io.xianzhi.system.bootstrap.dao.dataobj.OAuth2ClientDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * 客户端持久层
 *
 * @author Max
 * @since 1.0.0
 */
@Mapper
public interface OAuth2ClientMapper extends BaseMapper<OAuth2ClientDO> {

    /**
     * 根据客户端主键ID查询客户端
     *
     * @param id 客户端主键ID
     * @return 客户端
     */
    Optional<OAuth2ClientDO> selectOAuth2ClientById(@Param("id") String id);


    /**
     * 根据主键ID查询客户端信息
     *
     * @param id 主键ID
     * @return 客户端信息
     */
    OAuth2ClientDO queryById(@Param("id") String id);

    /**
     * 根据客户端ID查询客户端信息
     *
     * @param clientId 客户端ID
     * @return 客户端信息
     */
    OAuth2ClientDO queryByClientId(@Param("clientId") String clientId);


    /**
     * 判断客户端ID是否存在
     *
     * @param clientId 客户端ID
     * @param id       主键ID
     * @return 是否存在
     */
    boolean existsClientByIdAndIdNot(@Param("clientId") String clientId, @Param("id") String id);

    /**
     * 判断客户端名称是否存在
     *
     * @param clientName 客户端名称
     * @param id         客户端ID
     * @return 是否存在
     */
    boolean existsClientByNameAndIdNot(@Param("clientName") String clientName, @Param("id") String id);
}