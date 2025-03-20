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

import io.xianzhi.code.bootstrap.service.TagService;
import io.xianzhi.code.model.dto.TagDTO;
import io.xianzhi.code.model.page.TagPage;
import io.xianzhi.code.model.vo.TagVO;
import io.xianzhi.common.idempotent.annotations.Idempotent;
import io.xianzhi.core.result.ListResult;
import io.xianzhi.core.result.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Tag接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/c/tag")
public class TagController {

    /**
     * Tag接口
     */
    private final TagService tagService;


    /**
     * 分页查询tag列表
     *
     * @param tagPage 分页查询参数
     * @return tag列表
     */
    @PostMapping(value = "/pageTagList")
    public ResponseResult<ListResult<TagVO>> pageTagList(@RequestBody TagPage tagPage) {
        return ResponseResult.success();
    }

    /**
     * 新增tag  (幂等)
     *
     * @param tagDTO tag入参
     * @return 响应信息
     */
    @Idempotent
    @PostMapping(value = "/createTag")
    public ResponseResult<String> createTag(@RequestBody @Validated TagDTO tagDTO) {
        return ResponseResult.success();
    }


    /**
     * 删除tag
     *
     * @param id tag ID
     * @return 响应信息
     */
    @PostMapping(value = "/deletedTag")
    public ResponseResult<Object> deletedTag(@RequestParam String id) {
        return ResponseResult.success();
    }
}
