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

package io.xianzhi.system.bootstrap.controller;

import io.xianzhi.common.idempotent.annotations.Idempotent;
import io.xianzhi.core.result.ListResult;
import io.xianzhi.core.result.ResponseResult;
import io.xianzhi.core.validated.CreateGroup;
import io.xianzhi.core.validated.UpdateGroup;
import io.xianzhi.system.bootstrap.service.TemplateService;
import io.xianzhi.system.model.dto.TemplateDTO;
import io.xianzhi.system.model.page.TemplatePage;
import io.xianzhi.system.model.vo.TemplateVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 模板接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/s/template")
public class TemplateController {
    /**
     * 模板接口
     */
    private final TemplateService templateService;

    /**
     * 分页查询模板列表
     *
     * @param templatePage 分页查询参数
     * @return 模板列表
     */
    @PreAuthorize("@xz.hasPermission('system:template:list')")
    @PostMapping(value = "/pageTemplateList")
    public ResponseResult<ListResult<TemplateVO>> pageTemplateList(@RequestBody TemplatePage templatePage) {
        return ResponseResult.success(templateService.pageTemplateList(templatePage));
    }

    /**
     * 新增模板
     *
     * @param templateDTO 模板信息入参
     * @return 模板ID
     */
    @Idempotent
    @PreAuthorize("@xz.hasPermission('system:template:create')")
    @PostMapping(value = "/createTemplate")
    public ResponseResult<String> createTemplate(@RequestBody @Validated(value = CreateGroup.class) TemplateDTO templateDTO) {
        return ResponseResult.success(templateService.createTemplate(templateDTO));
    }

    /**
     * 更新模板
     *
     * @param templateDTO 模板信息入参
     * @return 响应信息
     */
    @PreAuthorize("@xz.hasPermission('system:template:update')")
    @PostMapping(value = "/updateTemplate")
    public ResponseResult<Object> updateTemplate(@RequestBody @Validated(value = UpdateGroup.class) TemplateDTO templateDTO) {
        templateService.updateTemplate(templateDTO);
        return ResponseResult.success();
    }

    /**
     * 删除模板
     *
     * @param ids 模板ID列表
     * @return 响应信息
     */
    @PreAuthorize("@xz.hasPermission('system:template:delete')")
    @PostMapping(value = "/deletedTemplate")
    public ResponseResult<Object> deletedTemplate(@RequestBody List<String> ids) {
        templateService.deletedTemplate(ids);
        return ResponseResult.success();
    }
}
