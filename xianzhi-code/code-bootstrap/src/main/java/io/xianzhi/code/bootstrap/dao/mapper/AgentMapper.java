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

package io.xianzhi.code.bootstrap.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.xianzhi.code.bootstrap.dao.dataobj.AgentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * agent持久层
 *
 * @author Max
 * @since 1.0.0
 */
@Mapper
public interface AgentMapper extends BaseMapper<AgentDO> {


    /**
     * 根据agentName和id查询是否存在
     *
     * @param agentName agent名称
     * @param id        agentId
     * @return 是否存在
     */
    boolean existsAgentByAgentNameAndIdNot(@Param("agentName") String agentName, @Param("id") String id);

    /**
     * 判断agentIp是否存在
     *
     * @param hostIp agentIp
     * @param id     agentId
     * @return 是否存在
     */
    boolean existsAgentByHostIpAndIdNot(@Param("hostIp") String hostIp, @Param("id") String id);

    /**
     * 根据agentId查询agent信息
     *
     * @param id agentId
     * @return agent信息
     */
    Optional<AgentDO> selectAgentById(@Param("id") String id);

}
