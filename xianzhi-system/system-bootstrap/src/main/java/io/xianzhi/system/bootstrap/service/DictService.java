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

package io.xianzhi.system.bootstrap.service;

import io.xianzhi.core.result.ListResult;
import io.xianzhi.system.model.dto.DictDTO;
import io.xianzhi.system.model.dto.DictItemDTO;
import io.xianzhi.system.model.page.DictPage;
import io.xianzhi.system.model.vo.DictItemVO;
import io.xianzhi.system.model.vo.DictVO;

import java.util.List;

/**
 * Dictionary Service
 * This interface defines the business logic for managing dictionary data within the application,
 * providing methods for querying, creating, updating, and deleting dictionaries and their items.
 *
 * @author Max
 * @since 1.0.0
 */
public interface DictService {

    /**
     * Query Dictionary List with Pagination
     * Retrieves a paginated list of dictionaries based on the provided parameters.
     *
     * @param dictPage The pagination query parameters.
     * @return The paginated list of dictionaries.
     */
    ListResult<DictVO> pageDictList(DictPage dictPage);

    /**
     * Create Dictionary
     * Creates a new dictionary with the provided information.
     *
     * @param dictDTO The dictionary information to be created.
     * @return The ID of the newly created dictionary.
     */
    String createDict(DictDTO dictDTO);

    /**
     * Update Dictionary
     * Updates the information of an existing dictionary.
     *
     * @param dictDTO The updated dictionary information.
     */
    void updateDict(DictDTO dictDTO);

    /**
     * Delete Dictionary
     * Deletes one or more dictionaries based on the provided IDs.
     *
     * @param ids The list of dictionary IDs to be deleted.
     */
    void deletedDict(List<String> ids);

    /**
     * Query Dictionary Items by Dictionary ID
     * Retrieves a list of dictionary items associated with the specified dictionary ID.
     *
     * @param dictId The ID of the dictionary to query items for.
     * @return The list of dictionary items.
     */
    List<DictItemVO> listItemByDictId(String dictId);

    /**
     * Create Dictionary Item
     * Creates a new dictionary item with the provided information.
     *
     * @param dictItemDTO The dictionary item information to be created.
     * @return The ID of the newly created dictionary item.
     */
    String createDictItem(DictItemDTO dictItemDTO);

    /**
     * Update Dictionary Item
     * Updates the information of an existing dictionary item.
     *
     * @param dictItemDTO The updated dictionary item information.
     */
    void updateDictItem(DictItemDTO dictItemDTO);

    /**
     * Delete Dictionary Item
     * Deletes one or more dictionary items based on the provided IDs.
     *
     * @param ids The list of dictionary item IDs to be deleted.
     */
    void deletedDictItem(List<String> ids);
}