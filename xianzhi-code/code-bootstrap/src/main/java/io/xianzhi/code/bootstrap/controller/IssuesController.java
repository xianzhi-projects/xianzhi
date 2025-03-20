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

import io.xianzhi.code.bootstrap.service.IssuesService;
import io.xianzhi.code.model.dto.IssuesDTO;
import io.xianzhi.code.model.page.IssuesPage;
import io.xianzhi.code.model.vo.IssuesVO;
import io.xianzhi.common.idempotent.annotations.Idempotent;
import io.xianzhi.core.result.ResponseResult;
import io.xianzhi.core.validated.CreateGroup;
import io.xianzhi.core.validated.UpdateGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Issues接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/c/issues")
public class IssuesController {

    /**
     * Issues接口
     */
    private final IssuesService issuesService;

    /**
     * 分页查询Issues列表
     *
     * @param issuesPage 分页查询参数
     * @return Issues列表
     */
    @PostMapping(value = "/pageIssuesList")
    public ResponseResult<IssuesVO> pageIssuesList(@RequestBody IssuesPage issuesPage) {
        return ResponseResult.success();
    }

    /**
     * 创建Issues  (幂等)
     *
     * @param issuesDTO Issues信息入参
     * @return Issues ID
     */
    @Idempotent
    @PostMapping(value = "/createIssues")
    public ResponseResult<String> createIssues(@RequestBody @Validated(value = CreateGroup.class) IssuesDTO issuesDTO) {
        return ResponseResult.success();
    }

    /**
     * 更新Issues
     *
     * @param issuesDTO Issues信息入参
     * @return 响应信息
     */
    @PostMapping(value = "/updateIssues")
    public ResponseResult<Object> updateIssues(@RequestBody @Validated(value = UpdateGroup.class) IssuesDTO issuesDTO) {
        return ResponseResult.success();
    }

    /**
     * 删除Issues
     *
     * @param id Issues ID
     * @return 响应信息
     */
    @PostMapping(value = "/deleteIssues")
    public ResponseResult<Object> deleteIssues(@RequestParam(value = "id") String id) {
        return ResponseResult.success();
    }
}
