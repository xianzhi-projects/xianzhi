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

package io.xianzhi.code.bootstrap.facade;

import io.xianzhi.code.facade.ProjectGroupFacade;
import io.xianzhi.code.model.dto.ProjectGroupDTO;
import io.xianzhi.code.model.vo.ProjectGroupVO;
import io.xianzhi.core.result.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目分组接口实现
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectGroupFacadeImpl implements ProjectGroupFacade {
    /**
     * 新增项目分组
     *
     * @param projectGroupDTO 项目分组入参
     * @return 响应信息
     */
    @Override
    public ResponseResult<String> createProjectGroup(ProjectGroupDTO projectGroupDTO) {
        return null;
    }

    /**
     * 获取当前用户所具有权限的分组
     *
     * @return 当前用户所具有权限的分组
     */
    @Override
    public ResponseResult<List<ProjectGroupVO>> getCurrentUserProjectGroupList() {
        return null;
    }
}
