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

package io.xianzhi.core.result;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 列表查询结果
 *
 * @author Max
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public class ListResult<E> {


    /**
     * 查询的列表数据
     *
     * @since 1.0.0
     */
    private List<E> list;

    /**
     * 查询的总数
     *
     * @since 1.0.0
     */
    private long total;

    /**
     * 创建一个空的返回结果
     *
     * @param <E> 返回结果泛型
     * @return 空的返回结果
     * @since 1.0.0
     */
    public static <E> ListResult<E> empty() {
        return new ListResult<>(List.of(), 0);
    }

    /**
     * 构建返回结果
     *
     * @param list  查询的列表数据
     * @param total 查询的总数
     * @param <E>   返回数据的泛型
     * @return 列表查询结果
     * @since 1.0.0
     */
    public static <E> ListResult<E> of(List<E> list, long total) {
        return new ListResult<>(list, total);
    }
}
