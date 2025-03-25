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

package io.xianzhi.code.model.dto;

import io.xianzhi.code.model.enums.AgentStatusEnum;
import io.xianzhi.code.model.enums.ProjectTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 项目信息入参
 *
 * @author Max
 * @since 1.0.0
 */
@Data
public class ProjectDTO implements Serializable {

    /**
     * 主键ID
     */
    private String id;
    /**
     * 项目分组ID
     */
    private String projectGroupId;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 项目路径
     */
    private String projectPath;
    /**
     * 项目描述
     */
    private String projectDesc;
    /**
     * 项目logo
     */
    private String projectLogo;
    /**
     * 项目可见性
     */
    private AgentStatusEnum projectVisibility;
    /**
     * 项目类型
     */
    private ProjectTypeEnum projectType;
}
