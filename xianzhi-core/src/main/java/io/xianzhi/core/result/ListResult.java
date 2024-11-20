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
 * List query result return
 *
 * @author Max
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public class ListResult<E> {


    /**
     * List query data
     *
     * @since 1.0.0
     */
    private List<E> list;

    /**
     * Query the total quantity for pagination
     *
     * @since 1.0.0
     */
    private long total;

    /**
     * Creates and returns an empty {@code ListResult} object.
     *
     * @param <E> The type of elements in the {@code ListResult}
     * @return An empty {@code ListResult} containing an empty list and a total count of 0
     * @since 1.0.0
     */
    public static <E> ListResult<E> empty() {
        return new ListResult<>(List.of(), 0);
    }

    /**
     * Constructs a {@code ListResult} object with the provided query result data and total count.
     *
     * @param list  The list of queried data items
     * @param total The total number of items matching the query
     * @param <E>   The type of elements in the list
     * @return A {@code ListResult} containing the query results and total count
     * @since 1.0.0
     */
    public static <E> ListResult<E> of(List<E> list, long total) {
        return new ListResult<>(list, total);
    }
}
