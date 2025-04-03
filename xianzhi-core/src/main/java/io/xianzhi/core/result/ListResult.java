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
 * List Response Result
 * This class serves as a container for the results of a list-based query, encapsulating both the
 * retrieved data list and the total count of available records. It is designed to provide a
 * structured and reusable way to return paginated or filtered list data from API calls or
 * database queries. The class is serializable, making it suitable for transmission over a network
 * or storage in a serialized form.
 *
 * @author Max
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public class ListResult<E> implements Serializable {

    /**
     * Queried Data
     * This field holds the list of data retrieved from the query. It is a generic list, allowing
     * the class to work with any type of data (denoted by the type parameter E). The list contains
     * the actual records returned for the current query, such as a subset of results in a paginated
     * response.
     */
    private List<E> list;

    /**
     * Total Count of Query
     * This field represents the total number of records that match the query criteria, regardless
     * of pagination or filtering applied to the list field. It provides context about the full
     * dataset size, which is useful for calculating the number of pages or informing the client
     * about the scope of the data.
     */
    private long total;

    /**
     * Return an Empty Result
     * This static method creates and returns an instance of ListResult representing an empty result set.
     * The returned object has an empty list (with no elements) and a total count of 0. This is useful
     * for scenarios where no data matches the query criteria, providing a consistent way to indicate
     * an absence of results.
     *
     * @param <E> The generic type parameter representing the type of elements in the list.
     * @return A new ListResult instance with an empty list and a total count of 0.
     */
    public static <E> ListResult<E> empty() {
        return new ListResult<>(List.of(), 0);
    }

    /**
     * Build a Response Result
     * This static method constructs a ListResult instance based on a provided list of data and a total
     * count. It offers a convenient way to create a response object with specific query results,
     * encapsulating both the data and metadata (total count) in a single object. This method is
     * typically used when query results are available and need to be packaged for return.
     *
     * @param list  The list of queried data to be included in the result, containing elements of type E.
     * @param total The total number of records matching the query, regardless of the list's contents.
     * @param <E>   The generic type parameter representing the type of elements in the returned list.
     * @return A new ListResult instance containing the specified list and total count.
     */
    public static <E> ListResult<E> of(List<E> list, long total) {
        return new ListResult<>(list, total);
    }
}