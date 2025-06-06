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

package io.xianzhi.code.facade;

import io.xianzhi.code.model.dto.ProjectDTO;
import io.xianzhi.core.result.ResponseResult;

/**
 * 项目接口
 *
 * @author Max
 * @since 1.0.0
 */
public interface ProjectFacade {

    /**
     * 新增项目
     * @param projectDTO 项目入参
     * @return 响应信息
     */
    ResponseResult<String> createProject(ProjectDTO projectDTO);
}
