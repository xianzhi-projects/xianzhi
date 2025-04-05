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
 * 国际化信息出参
 *
 * @author Max
 * @since 1.0.0
 */
@Data
public class I18nVO implements Serializable {

    /**
     * 主键ID
     */
    private String id;
    /**
     * 国际化名称
     */
    private String messageTitle;
    /**
     * 国际化key
     */
    private String messageKey;
    /**
     * 国际描述
     */
    private String messageDesc;
    /**
     * 创建人
     */
    private UserVO createBY;
    /**
     * 创建时间
     */
    private LocalDateTime createAt;
}
