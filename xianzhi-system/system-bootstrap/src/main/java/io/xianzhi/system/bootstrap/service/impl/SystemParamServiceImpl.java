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

import io.xianzhi.core.exception.BusinessException;
import io.xianzhi.core.result.ListResult;
import io.xianzhi.system.bootstrap.business.SystemParamBusiness;
import io.xianzhi.system.bootstrap.dao.dataobj.SystemParamDO;
import io.xianzhi.system.bootstrap.dao.mapper.SystemParamMapper;
import io.xianzhi.system.bootstrap.service.SystemParamService;
import io.xianzhi.system.model.dto.SystemParamDTO;
import io.xianzhi.system.model.page.SystemParamPage;
import io.xianzhi.system.model.vo.SystemParamVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 系统参数接口实现
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SystemParamServiceImpl implements SystemParamService {

    /**
     * 系统参数Mapper
     */
    private final SystemParamMapper systemParamMapper;

    /**
     * 系统参数业务类
     */
    private final SystemParamBusiness systemParamBusiness;

    /**
     * 分页查询系统参数
     *
     * @param systemParamPage 系统参数查询条件
     * @return 系统参数列表
     */
    @Override
    public ListResult<SystemParamVO> pageSystemParamList(SystemParamPage systemParamPage) {
        return null;
    }

    /**
     * 创建系统参数
     *
     * @param systemParamDTO 系统参数入参
     * @return 系统参数ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createSystemParam(SystemParamDTO systemParamDTO) {
        SystemParamDO systemParamDO = checkedSystemParam(systemParamDTO);
        systemParamMapper.insert(systemParamDO);
        return systemParamDO.getId();
    }

    /**
     * 更新系统参数
     *
     * @param systemParamDTO 系统参数入参
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSystemParam(SystemParamDTO systemParamDTO) {
        SystemParamDO systemParamDO = checkedSystemParam(systemParamDTO);
        systemParamMapper.updateById(systemParamDO);

    }

    /**
     * 删除系统参数
     *
     * @param ids 系统参数ID列表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSystemParam(List<String> ids) {
        systemParamMapper.deletedSystemByIds(ids);
    }

    /**
     * 校验系统参数
     *
     * @param systemParamDTO 系统参数入参
     * @return 系统参数
     */
    private SystemParamDO checkedSystemParam(SystemParamDTO systemParamDTO) {
        SystemParamDO systemParam;
        if (StringUtils.hasText(systemParamDTO.getId())) {
            systemParam = systemParamMapper.selectSystemParamById(systemParamDTO.getId()).orElseThrow(() -> new BusinessException("系统参数不存在"));
        } else {
            systemParam = new SystemParamDO();
            if (systemParamMapper.existParamByCode(systemParamDTO.getParamCode())) {
                throw new BusinessException("参数编码已存在");
            }
        }
        if (systemParamMapper.existParamByNameAndIdNot(systemParamDTO.getParamName(), systemParamDTO.getId())) {
            throw new BusinessException("参数名称已存在");
        }
        systemParam.setParamCode(systemParamDTO.getParamCode());
        systemParam.setParamName(systemParamDTO.getParamName());
        systemParam.setParamValue(systemParamDTO.getParamValue());
        systemParam.setParamDesc(systemParamDTO.getParamDesc());
        systemParam.setParamType(systemParamDTO.getParamType());
        return systemParam;
    }
}
