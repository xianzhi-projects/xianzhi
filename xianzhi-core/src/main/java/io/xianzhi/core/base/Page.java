/*
 *  Copyright 2024 XianZhi Group
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
 *
 */

package io.xianzhi.core.base;

import lombok.Data;

import java.io.Serializable;

/**
 * 基础分页对象，当查询列表的时候查询对象继承该对象
 *
 * @author Max
 * @since 1.0.0
 */
@Data
public class Page implements Serializable {

    /**
     * 默认分页条数
     *
     * @since 1.0.0
     */
    private Integer pageNo = 1;
    /**
     * 默认每页展示条数
     *
     * @since 1.0.0
     */
    private Integer pageSize = 10;
}
