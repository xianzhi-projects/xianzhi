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

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 添加成员入参
 *
 * @author Max
 * @since 1.0.0
 */
@Data
public class AddMemberDTO implements Serializable {

    /**
     * 用户ID
     */
    private String userId;
    /**
     * 所属角色
     */
    private String roleCode;
    /**
     * 过期时间
     */
    private LocalDate expireDate;
}
