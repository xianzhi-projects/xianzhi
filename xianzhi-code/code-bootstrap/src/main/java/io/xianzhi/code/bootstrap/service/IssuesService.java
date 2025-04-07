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

import io.xianzhi.code.model.dto.IssuesDTO;
import io.xianzhi.code.model.page.IssuesPage;
import io.xianzhi.code.model.vo.IssuesVO;

/**
 * Issues接口
 *
 * @author Max
 * @since 1.0.0
 */
public interface IssuesService {
    /**
     * 分页查询Issues列表
     *
     * @param issuesPage 分页查询参数
     * @return Issues列表
     */
    IssuesVO pageIssuesList(IssuesPage issuesPage);

    /**
     * 创建Issues  (幂等)
     *
     * @param issuesDTO Issues信息入参
     * @return Issues ID
     */
    String createIssues(IssuesDTO issuesDTO);

    /**
     * 更新Issues
     *
     * @param issuesDTO Issues信息入参
     */
    void updateIssues(IssuesDTO issuesDTO);

    /**
     * 删除Issues
     *
     * @param id Issues ID
     */
    void deleteIssues(String id);

}
