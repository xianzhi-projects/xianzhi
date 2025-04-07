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

package io.xianzhi.code.bootstrap.service;

import io.xianzhi.code.model.dto.PullRequestDTO;
import io.xianzhi.code.model.page.PullRequestPage;
import io.xianzhi.code.model.vo.PullRequestVO;
import io.xianzhi.core.result.ListResult;

/**
 * PR接口
 *
 * @author Max
 * @since 1.0.0
 */
public interface PullRequestService {
    /**
     * 分页查询合并请求列表
     *
     * @param pullRequestPage 分页查询参数
     * @return 合并请求列表
     */
    ListResult<PullRequestVO> pagePullRequestList(PullRequestPage pullRequestPage);

    /**
     * 创建合并请求
     *
     * @param pullRequestDTO 合并请求入参
     * @return 合并请求ID
     */
    String createPullRequest(PullRequestDTO pullRequestDTO);

    /**
     * 更新合并请求
     *
     * @param pullRequestDTO 合并请求入参
     */
    void updatePullRequest(PullRequestDTO pullRequestDTO);

    /**
     * 删除合并请求
     *
     * @param id 合并请求ID
     */
    void deletedPullRequest(String id);

    /**
     * 关闭合并请求
     *
     * @param id 合并请求ID
     */
    void closePullRequest(String id);
}
