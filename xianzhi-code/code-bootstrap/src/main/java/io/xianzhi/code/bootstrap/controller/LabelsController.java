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

package io.xianzhi.code.bootstrap.controller;

import io.xianzhi.code.bootstrap.service.LabelService;
import io.xianzhi.code.model.dto.LabelDTO;
import io.xianzhi.code.model.page.LabelPage;
import io.xianzhi.code.model.vo.LabelVO;
import io.xianzhi.common.idempotent.annotations.Idempotent;
import io.xianzhi.core.result.ListResult;
import io.xianzhi.core.result.ResponseResult;
import io.xianzhi.core.validated.CreateGroup;
import io.xianzhi.core.validated.UpdateGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Labels 接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/c/labels")
public class LabelsController {

    /**
     * 标签接口
     */
    private final LabelService labelService;

    /**
     * 分页查询标签列表
     *
     * @param labelPage 分页查询参数
     * @return 标签列表
     */
    @PostMapping(value = "/pageLabelList")
    public ResponseResult<ListResult<LabelVO>> pageLabelList(@RequestBody LabelPage labelPage) {
        return ResponseResult.success(labelService.pageLabelList(labelPage));
    }

    /**
     * 创建标签  (幂等)
     *
     * @param labelDTO 标签信息入参
     * @return 标签ID
     */
    @Idempotent
    @PostMapping(value = "/createLabel")
    public ResponseResult<String> createLabel(@RequestBody @Validated(value = CreateGroup.class) LabelDTO labelDTO) {
        return ResponseResult.success(labelService.createLabel(labelDTO));
    }

    /**
     * 更新标签
     *
     * @param labelDTO 标签信息入参
     * @return 响应信息
     */
    @PostMapping(value = "/updateLabel")
    public ResponseResult<Object> updateLabel(@RequestBody @Validated(value = UpdateGroup.class) LabelDTO labelDTO) {
        labelService.updateLabel(labelDTO);
        return ResponseResult.success();
    }

    /**
     * 删除标签
     *
     * @param id 标签ID
     * @return 响应信息
     */
    @PostMapping(value = "/deletedLabel")
    public ResponseResult<Object> deletedLabel(@RequestParam(value = "id") String id) {
        labelService.deletedLabel(id);
        return ResponseResult.success();
    }
}
