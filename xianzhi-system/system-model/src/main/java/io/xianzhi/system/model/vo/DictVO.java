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

package io.xianzhi.system.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 字典出参
 *
 * @author Max
 * @since 1.0.0
 */
@Data
public class DictVO implements Serializable {

    /**
     * 字典ID
     */
    private String id;
    /**
     * 字典名称
     */
    private String dictName;
    /**
     * 字典描述
     */
    private String dictDesc;
    /**
     * 字典编码
     */
    private String dictCode;
    /**
     * 创建时间
     */
    private LocalDateTime createAt;
    /**
     * 新增用户
     */
    private UserVO createBy;
}
