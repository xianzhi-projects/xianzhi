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

package io.xianzhi.core.base;

import lombok.Data;

import java.io.Serializable;

/**
 * Basic Pagination
 * This class encapsulates the fundamental parameters required for implementing paginated queries in the system.
 * It provides properties to specify the current page number and the number of records to display per page,
 * facilitating efficient data retrieval and display in a paginated format. The class is designed to be
 * serializable, allowing it to be easily transmitted or stored as needed.
 *
 * @author Max
 * @since 1.0.0
 */
@Data
public class Page implements Serializable {

    /**
     * Current Page Number
     * This field represents the page number currently being requested or viewed in a paginated query.
     * It is initialized with a default value of 1, meaning that if no specific page number is provided,
     * the first page will be assumed. This ensures a sensible starting point for pagination.
     */
    private Integer pageNo = 1;

    /**
     * Page Size
     * This field specifies the number of records to be returned per page in a paginated query.
     * It has a default value of 20, which means that, unless otherwise specified, each page will
     * contain up to 20 records. This default value provides a balance between performance and usability,
     * but it can be adjusted based on specific requirements.
     */
    private Integer pageSize = 20;
}