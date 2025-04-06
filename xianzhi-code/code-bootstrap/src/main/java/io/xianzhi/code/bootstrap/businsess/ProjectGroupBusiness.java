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

package io.xianzhi.code.bootstrap.businsess;

import io.xianzhi.code.bootstrap.dao.dataobj.ProjectGroupDO;
import io.xianzhi.code.bootstrap.dao.mapper.ProjectGroupMapper;
import io.xianzhi.code.model.code.ProjectGroupCode;
import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.exception.BusinessException;
import io.xianzhi.core.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * 项目分组业务类
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ProjectGroupBusiness {

    /**
     * 项目分组持久层
     */
    private final ProjectGroupMapper projectGroupMapper;

    /**
     * 根据项目分组ID查询分组信息，如果不存在则抛出默认异常响应信息
     *
     * @param groupId 项目分组ID
     * @return 项目分组信息
     */
    public ProjectGroupDO getProjectGroupByIdOrThrow(String groupId) {
        return getProjectGroupByIdOrThrow(groupId, ProjectGroupCode.PROJECT_GROUP_NOT_EXIST);
    }

    /**
     * 根据项目分组ID查询分组信息，如果不存在则抛出指定异常响应信息
     *
     * @param groupId 项目分组ID
     * @param result  响应信息
     * @return 项目信息
     */
    public ProjectGroupDO getProjectGroupByIdOrThrow(String groupId, Result result) {
        return getProjectGroupById(groupId).orElseThrow(() -> new BusinessException(result));
    }

    /**
     * 根据项目ID查询项目信息
     *
     * @param groupId 项目分组ID
     * @return 项目信息
     */
    public Optional<ProjectGroupDO> getProjectGroupById(String groupId) {
        if (StringUtils.hasText(groupId)) {
            return projectGroupMapper.selectProjectGroupById(groupId);
        }
        log.error("query by groupId error,groupId is blank");
        throw new BusinessException(CommonCode.PARAM_CHECK_ERROR);
    }
}
