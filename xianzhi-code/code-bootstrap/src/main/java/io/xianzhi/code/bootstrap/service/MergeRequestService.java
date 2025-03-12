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

import io.xianzhi.code.model.dto.MergeRequestDTO;
import io.xianzhi.code.model.page.MergeRequestPage;
import io.xianzhi.code.model.vo.MergeRequestVO;
import io.xianzhi.core.result.ListResult;

/**
 * MR接口
 *
 * @author Max
 * @since 1.0.0
 */
public interface MergeRequestService {
    /**
     * 分页查询合并请求列表
     *
     * @param mergeRequestPage 分页查询参数
     * @return 合并请求列表
     */
    ListResult<MergeRequestVO> pageMergeRequestList(MergeRequestPage mergeRequestPage);

    /**
     * 创建合并请求
     *
     * @param mergeRequestDTO 合并请求入参
     * @return 合并请求ID
     */
    String createMergeRequest(MergeRequestDTO mergeRequestDTO);

    /**
     * 更新合并请求
     *
     * @param mergeRequestDTO 合并请求入参
     */
    void updateMergeRequest(MergeRequestDTO mergeRequestDTO);

    /**
     * 删除合并请求
     *
     * @param id 合并请求ID
     */
    void deletedMergeRequest(String id);

    /**
     * 关闭合并请求
     *
     * @param id 合并请求ID
     */
    void closeMergeRequest(String id);
}
