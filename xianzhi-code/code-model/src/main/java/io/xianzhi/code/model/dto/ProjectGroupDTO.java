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

import io.xianzhi.code.model.enums.VisibilityEnum;
import lombok.Data;

/**
 * 项目分组实体
 *
 * @author Max
 * @since 1.0.0
 */
@Data
public class ProjectGroupDTO {

    /**
     * 分组ID
     */
    private String id;
    /**
     * 分组名称
     */
    private String groupName;
    /**
     * 分组描述
     */
    private String groupDesc;
    /**
     * 分组路径
     */
    private String groupPath;
    /**
     * 父级ID
     */
    private String parentId;
    /**
     * 分组logo
     */
    private String groupLogo;
    /**
     * 分组可见性
     */
    private VisibilityEnum visibility;
}
