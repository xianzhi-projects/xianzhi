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

import io.xianzhi.core.result.ListResult;
import io.xianzhi.system.bootstrap.dao.mapper.TemplateMapper;
import io.xianzhi.system.bootstrap.service.TemplateService;
import io.xianzhi.system.model.dto.TemplateDTO;
import io.xianzhi.system.model.page.TemplatePage;
import io.xianzhi.system.model.vo.TemplateVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 模板接口实现
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {

    /**
     * 模板信息持久层
     */
    private final TemplateMapper templateMapper;

    /**
     * 分页查询模板列表
     *
     * @param templatePage 分页查询参数
     * @return 模板列表
     */
    @Override
    public ListResult<TemplateVO> pageTemplateList(TemplatePage templatePage) {
        return null;
    }

    /**
     * 新增模板
     *
     * @param templateDTO 模板信息入参
     * @return 模板ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createTemplate(TemplateDTO templateDTO) {
        return "";
    }

    /**
     * 更新模板
     *
     * @param templateDTO 模板信息入参
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTemplate(TemplateDTO templateDTO) {

    }

    /**
     * 删除模板
     *
     * @param ids 模板ID列表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletedTemplate(List<String> ids) {

    }
}
