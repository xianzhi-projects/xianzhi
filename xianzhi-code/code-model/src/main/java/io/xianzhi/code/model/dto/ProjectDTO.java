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

import io.xianzhi.code.model.enums.ProjectTypeEnum;
import io.xianzhi.code.model.enums.VisibilityEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
    @Length(max = 20, message = "code.project.id.too.long")
    @NotBlank(message = "code.project.id.not.blank")
    private String id;
    /**
     * 项目分组ID
     */
    private String projectGroupId;
    /**
     * 项目名称
     */
    @NotBlank(message = "code.project.name.not.blank")
    private String projectName;
    /**
     * 项目路径
     */
    @NotBlank(message = "code.project.path.not.blank")
    private String projectPath;
    /**
     * 项目描述
     */
    @Length(max = 255, message = "code.project.desc.too.long")
    private String projectDesc;
    /**
     * 项目logo
     */
    @Length(max = 255, message = "code.project.logo.too.long")
    private String projectLogo;
    /**
     * 项目可见性
     */
    @NotNull(message = "code.project.visibility.not.null")
    private VisibilityEnum projectVisibility;
    /**
     * 项目类型
     */
    @NotNull(message = "code.project.type.not.null")
    private ProjectTypeEnum projectType;
    /**
     * 是否添加readME文件
     */
    private String readMETemplate;

    /**
     * 是否添加gitignore模板
     */
    private String gitignoreTemplate;

    /**
     * 是否创建合并请求模板
     */
    private String pullRequestTemplate;

    /**
     * 分支模型
     */
    private String branchModel;
}
