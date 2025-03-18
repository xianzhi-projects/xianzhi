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
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseResult<ListResult<LabelVO>> pageLabelList(LabelPage labelPage) {
        return ResponseResult.success();
    }

    /**
     * 创建标签
     *
     * @param labelDTO 标签信息入参
     * @return 标签ID
     */
    @Idempotent
    @PostMapping(value = "/createLabel")
    public ResponseResult<String> createLabel(LabelDTO labelDTO) {
        return ResponseResult.success();
    }

    /**
     * 更新标签
     *
     * @param labelDTO 标签信息入参
     * @return 响应信息
     */
    @PostMapping(value = "/updateLabel")
    public ResponseResult<Object> updateLabel(LabelDTO labelDTO) {
        return ResponseResult.success();
    }

    /**
     * 删除标签
     *
     * @param id 标签ID
     * @return 响应信息
     */
    @PostMapping(value = "/deletedLabel")
    public ResponseResult<Object> deletedLabel(String id) {
        return ResponseResult.success();
    }
}
