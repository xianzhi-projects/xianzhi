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

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import io.xianzhi.code.bootstrap.dao.dataobj.AgentDO;
import io.xianzhi.code.bootstrap.dao.mapper.AgentDetailsMapper;
import io.xianzhi.code.bootstrap.dao.mapper.AgentGroupMapper;
import io.xianzhi.code.bootstrap.dao.mapper.AgentMapper;
import io.xianzhi.code.bootstrap.service.AgentService;
import io.xianzhi.code.model.dto.AgentDTO;
import io.xianzhi.code.model.enums.AgentStatusEnum;
import io.xianzhi.code.model.page.AgentPage;
import io.xianzhi.code.model.vo.AgentVO;
import io.xianzhi.common.jsch.JschUtils;
import io.xianzhi.core.exception.BusinessException;
import io.xianzhi.core.result.ListResult;
import io.xianzhi.core.thread.XianZhiCallable;
import io.xianzhi.system.model.enums.HostAuthTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * agent实现
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {
    /**
     * agent持久层
     */
    private final AgentMapper agentMapper;

    /**
     * agent详情持久层
     */
    private final AgentDetailsMapper agentDetailsMapper;

    /**
     * agent 分组持久层
     */
    private final AgentGroupMapper agentGroupMapper;

    /**
     * Agent相关线程池
     */
    private final ThreadPoolTaskExecutor agentThreadPoolTaskExecutor;

    /**
     * 分页查询agent列表
     *
     * @param agentPage 分页查询参数
     * @return agent列表
     */
    @Override
    public ListResult<AgentVO> pageAgentList(AgentPage agentPage) {
        return null;
    }

    /**
     * 薪资agent
     *
     * @param agentDTO agent入参
     * @return 响应信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createAgent(AgentDTO agentDTO) {
        AgentDO agent = checkedAgentDTO(agentDTO);
        agentMapper.insert(agent);

        return agent.getId();
    }

    /**
     * 更新agent
     *
     * @param agentDTO agent入参
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAgent(AgentDTO agentDTO) {
        AgentDO agent = checkedAgentDTO(agentDTO);
        agentMapper.updateById(agent);
    }

    /**
     * 删除agent
     *
     * @param id agentID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletedAgent(String id) {

    }

    /**
     * 安装agent
     *
     * @param id agentId
     */
    @Override
    public void installAgent(String id) {
        AgentDO agent = agentMapper.selectAgentById(id).orElseThrow(() -> new BusinessException("agent不存在"));
        AgentStatusEnum agentStatus = AgentStatusEnum.getAgentStatusByCode(agent.getAgentStatus());
        assert null != agentStatus;
        if (!AgentStatusEnum.WAIT_FOR_INSTALL.getCode().equals(agent.getAgentStatus()) && !AgentStatusEnum.INSTALL_FAILED.getCode().equals(agent.getAgentStatus())) {
            log.error("安装Agent:{}失败,原因:{},当前agent状态:{}", id, "agent状态不正确", agentStatus.getDesc());
            throw new BusinessException("agent状态不正确");
        }
        agentThreadPoolTaskExecutor.submit(new XianZhiCallable<>(() -> {
            log.debug("开始安装agent...");
            String authType = agent.getAuthType();
            Session session;
            try {
                if (HostAuthTypeEnum.PASSWORD.getCode().equals(authType)) {
                    session = JschUtils.getSessionByPassword(agent.getHostIp(), agent.getHostPort(), agent.getHostUsername(), agent.getHostPassword());
                    log.debug("密码认证获取Session成功...");
                } else if (HostAuthTypeEnum.PRIVATE_KEY.getCode().equals(authType)) {
                    session = JschUtils.getSessionByPrivateKey(agent.getHostIp(), agent.getHostPort(), agent.getHostUsername(), agent.getHostPrivateKey());
                    log.debug("私钥认证获取Session成功...");
                } else {

                    log.debug("凭证获取Session成功...");
                }
            } catch (JSchException exception) {
                log.error("安装agent:{}失败,原因:{}", id, "获取主机Session失败", exception);
                agent.setAgentStatus(AgentStatusEnum.INSTALL_FAILED.getCode());
                agentMapper.updateById(agent);
                return null;
            } catch (Exception exception) {
                log.error("获取主机Session失败,原因:{}", exception.getMessage(), exception);
                agent.setAgentStatus(AgentStatusEnum.INSTALL_FAILED.getCode());
                agentMapper.updateById(agent);
                return null;
            }
            log.debug("主机:{},获取Session成功", agent.getHostIp());
            log.debug("开始创建工作目录:{}", agent.getWorkDir());
            String command = "mkdir -p " + agent.getWorkDir();


            log.debug("agent:{},安装成功", id);
            agent.setAgentStatus(callAgent(agent) ? AgentStatusEnum.ONLINE.getCode() : AgentStatusEnum.OFFLINE.getCode());
            agentMapper.updateById(agent);
            return null;
        }));

    }


    private boolean callAgent(AgentDO agent) {
        return true;

    }


    /**
     * 检查agentDTO
     *
     * @param agentDTO agentDTO
     * @return agentDO
     */
    private AgentDO checkedAgentDTO(AgentDTO agentDTO) {
        AgentDO agent;
        if (StringUtils.hasText(agentDTO.getId())) {
            agent = agentMapper.selectAgentById(agentDTO.getId()).orElseThrow(() -> new BusinessException("agent不存在"));
            AgentStatusEnum agentStatus = AgentStatusEnum.getAgentStatusByCode(agent.getAgentStatus());
            assert null != agentStatus;
        } else {
            agent = new AgentDO();
            agent.setAgentStatus(AgentStatusEnum.WAIT_FOR_INSTALL.getCode());
        }
        return agent;
    }
}
