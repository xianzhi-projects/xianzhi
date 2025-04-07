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

import io.xianzhi.code.bootstrap.service.PullRequestService;
import io.xianzhi.code.model.dto.PullRequestDTO;
import io.xianzhi.code.model.page.PullRequestPage;
import io.xianzhi.code.model.vo.PullRequestVO;
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
@RequestMapping(value = "/c/pull_request")
public class PullRequestController {

    /**
     * 合并请求接口
     */
    private final PullRequestService pullRequestService;

    /**
     * 分页查询合并请求列表
     *
     * @param pullRequestPage 分页查询参数
     * @return 合并请求列表
     */
    @PostMapping(value = "/pagePullRequestList")
    public ResponseResult<ListResult<PullRequestVO>> pagePullRequestList(@RequestBody PullRequestPage pullRequestPage) {
        return ResponseResult.success(pullRequestService.pagePullRequestList(pullRequestPage));
    }

    /**
     * 创建合并请求  (幂等)
     *
     * @param pullRequestDTO 合并请求入参
     * @return 合并请求ID
     */
    @Idempotent
    @PostMapping(value = "/createPullRequest")
    public ResponseResult<String> createPullRequest(@RequestBody @Validated(value = CreateGroup.class) PullRequestDTO pullRequestDTO) {
        return ResponseResult.success(pullRequestService.createPullRequest(pullRequestDTO));
    }

    /**
     * 更新合并请求
     *
     * @param pullRequestDTO 合并请求入参
     * @return 响应信息
     */
    @PostMapping(value = "/updatePullRequest")
    public ResponseResult<Object> updatePullRequest(@RequestBody @Validated(value = UpdateGroup.class) PullRequestDTO pullRequestDTO) {
        pullRequestService.updatePullRequest(pullRequestDTO);
        return ResponseResult.success();
    }

    /**
     * 删除合并请求
     *
     * @param id 合并请求ID
     * @return 响应信息
     */
    @PostMapping(value = "/deletePullRequest")
    public ResponseResult<Object> deletePullRequest(@RequestParam(value = "id") String id) {
        pullRequestService.deletedPullRequest(id);
        return ResponseResult.success();
    }

    /**
     * 关闭合并请求
     *
     * @param id 合并请求ID
     * @return 响应信息
     */
    @PostMapping(value = "/closePullRequest")
    public ResponseResult<Object> closePullRequest(@RequestParam(value = "id") String id) {
        pullRequestService.closePullRequest(id);
        return ResponseResult.success();
    }


}
