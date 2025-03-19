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

package io.xianzhi.system.bootstrap.service;

import io.xianzhi.core.result.ListResult;
import io.xianzhi.system.model.dto.SystemParamDTO;
import io.xianzhi.system.model.page.SystemParamPage;
import io.xianzhi.system.model.vo.SystemParamVO;

import java.util.List;

/**
 * 系统参数接口
 *
 * @author Max
 * @since 1.0.0
 */
public interface SystemParamService {

    /**
     * 分页查询系统参数
     *
     * @param systemParamPage 系统参数查询条件
     * @return 系统参数列表
     */
    ListResult<SystemParamVO> pageSystemParamList(SystemParamPage systemParamPage);
    /**
     * 创建系统参数
     *
     * @param systemParamDTO 系统参数入参
     * @return 系统参数ID
     */
    String createSystemParam(SystemParamDTO systemParamDTO);
    /**
     * 更新系统参数
     *
     * @param systemParamDTO 系统参数入参
     */
    void updateSystemParam(SystemParamDTO systemParamDTO);
    /**
     * 删除系统参数
     *
     * @param ids 系统参数ID列表
     */
    void deleteSystemParam( List<String> ids);

}
