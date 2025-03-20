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

import io.xianzhi.code.bootstrap.service.MergeRequestService;
import io.xianzhi.code.model.dto.MergeRequestDTO;
import io.xianzhi.code.model.page.MergeRequestPage;
import io.xianzhi.code.model.vo.MergeRequestVO;
import io.xianzhi.common.idempotent.annotations.Idempotent;
import io.xianzhi.core.result.ListResult;
import io.xianzhi.core.result.ResponseResult;
import io.xianzhi.core.validated.CreateGroup;
import io.xianzhi.core.validated.UpdateGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 合并请求接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/c/merge_request")
public class MergeRequestController {

    /**
     * 合并请求接口
     */
    private final MergeRequestService mergeRequestService;

    /**
     * 分页查询合并请求列表
     *
     * @param mergeRequestPage 分页查询参数
     * @return 合并请求列表
     */
    @PostMapping(value = "/pageMergeRequestList")
    public ResponseResult<ListResult<MergeRequestVO>> pageMergeRequestList(@RequestBody MergeRequestPage mergeRequestPage) {
        return ResponseResult.success(mergeRequestService.pageMergeRequestList(mergeRequestPage));
    }

    /**
     * 创建合并请求  (幂等)
     *
     * @param mergeRequestDTO 合并请求入参
     * @return 合并请求ID
     */
    @Idempotent
    @PostMapping(value = "/createMergeRequest")
    public ResponseResult<String> createMergeRequest(@RequestBody @Validated(value = CreateGroup.class) MergeRequestDTO mergeRequestDTO) {
        return ResponseResult.success(mergeRequestService.createMergeRequest(mergeRequestDTO));
    }

    /**
     * 更新合并请求
     *
     * @param mergeRequestDTO 合并请求入参
     * @return 响应信息
     */
    @PostMapping(value = "/updateMergeRequest")
    public ResponseResult<Object> updateMergeRequest(@RequestBody @Validated(value = UpdateGroup.class) MergeRequestDTO mergeRequestDTO) {
        mergeRequestService.updateMergeRequest(mergeRequestDTO);
        return ResponseResult.success();
    }

    /**
     * 删除合并请求
     *
     * @param id 合并请求ID
     * @return 响应信息
     */
    @PostMapping(value = "/deleteMergeRequest")
    public ResponseResult<Object> deleteMergeRequest(@RequestParam(value = "id") String id) {
        mergeRequestService.deletedMergeRequest(id);
        return ResponseResult.success();
    }

    /**
     * 关闭合并请求
     *
     * @param id 合并请求ID
     * @return 响应信息
     */
    @PostMapping(value = "/closeMergeRequest")
    public ResponseResult<Object> closeMergeRequest(@RequestParam(value = "id") String id) {
        mergeRequestService.closeMergeRequest(id);
        return ResponseResult.success();
    }


}
