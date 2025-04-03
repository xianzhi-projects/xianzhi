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

package io.xianzhi.core.result;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 列表返回结果
 * List Response Result
 * 该类用于封装列表查询的返回结果，包括数据列表和总数。
 * This class is used to encapsulate the response result of a list query, including the data list and total count.
 *
 * @author Max
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public class ListResult<E> implements Serializable {

    /**
     * 查询的数据
     * Queried Data
     */
    private List<E> list;

    /**
     * 查询的总数
     * Total Count of Query
     */
    private long total;

    /**
     * 返回空结果
     * Return an Empty Result
     * 该方法用于创建一个空的列表返回结果，列表为空，总数为0。
     * This method creates an empty list result with an empty list and a total of 0.
     *
     * @param <E> 泛型 / Generic type
     * @return 空列表结果 / Empty list result
     */
    public static <E> ListResult<E> empty() {
        return new ListResult<>(List.of(), 0);
    }

    /**
     * 构建返回结果
     * Build a Response Result
     * 该方法根据提供的列表和总数构建一个列表返回结果。
     * This method builds a list result based on the provided list and total count.
     *
     * @param list  查询的列表 / Queried list
     * @param total 查询的总数 / Total count of query
     * @param <E>   返回的数据泛型 / Generic type of returned data
     * @return 列表结果 / List result
     */
    public static <E> ListResult<E> of(List<E> list, long total) {
        return new ListResult<>(list, total);
    }
}