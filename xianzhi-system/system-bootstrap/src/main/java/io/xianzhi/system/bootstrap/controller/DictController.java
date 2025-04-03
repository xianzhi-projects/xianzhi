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

package io.xianzhi.system.bootstrap.controller;

import io.xianzhi.common.idempotent.annotations.Idempotent;
import io.xianzhi.core.result.ListResult;
import io.xianzhi.core.result.ResponseResult;
import io.xianzhi.core.validated.CreateGroup;
import io.xianzhi.core.validated.UpdateGroup;
import io.xianzhi.system.bootstrap.service.DictService;
import io.xianzhi.system.model.dto.DictDTO;
import io.xianzhi.system.model.dto.DictItemDTO;
import io.xianzhi.system.model.page.DictPage;
import io.xianzhi.system.model.vo.DictItemVO;
import io.xianzhi.system.model.vo.DictVO;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Dictionary Controller
 * This class serves as a controller for handling HTTP requests related to dictionary management
 * within the application. It provides endpoints for querying, creating, updating, and deleting
 * dictionary and dictionary item information, interacting with the dictionary service layer to
 * perform these operations.
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/s/dict")
public class DictController {

    /**
     * Dictionary Service
     * This field provides access to the dictionary service layer, which encapsulates the business
     * logic for managing dictionary data.
     */
    private final DictService dictService;

    /**
     * Query Dictionary List with Pagination
     * This method retrieves a paginated list of dictionaries based on the provided parameters.
     *
     * @param dictPage The pagination query parameters.
     * @return A response containing the paginated list of dictionaries.
     */
    @PreAuthorize("@xz.hasPermission('system:dict:page')")
    @PostMapping(value = "/pageDictList")
    public ResponseResult<ListResult<DictVO>> pageDictList(@RequestBody DictPage dictPage) {
        return ResponseResult.success(dictService.pageDictList(dictPage));
    }

    /**
     * Create Dictionary (Idempotent)
     * This method creates a new dictionary with the provided information, ensuring idempotency
     * to prevent duplicate submissions.
     *
     * @param dictDTO The dictionary information to be created.
     * @return A response containing the ID of the newly created dictionary.
     */
    @Idempotent
    @PreAuthorize("@xz.hasPermission('system:dict:create')")
    @PostMapping(value = "/createDict")
    public ResponseResult<String> createDict(@RequestBody @Validated(value = CreateGroup.class) DictDTO dictDTO) {
        return ResponseResult.success(dictService.createDict(dictDTO));
    }

    /**
     * Update Dictionary
     * This method updates the information of an existing dictionary.
     *
     * @param dictDTO The updated dictionary information.
     * @return A success response indicating the operation was completed.
     */
    @PreAuthorize("@xz.hasPermission('system:dict:update')")
    @PostMapping(value = "/updateDict")
    public ResponseResult<Object> updateDict(@RequestBody @Validated(value = UpdateGroup.class) DictDTO dictDTO) {
        dictService.updateDict(dictDTO);
        return ResponseResult.success();
    }

    /**
     * Delete Dictionary
     * This method deletes one or more dictionaries based on the provided IDs.
     *
     * @param ids The list of dictionary IDs to be deleted.
     * @return A success response indicating the operation was completed.
     */
    @PreAuthorize("@xz.hasPermission('system:dict:delete')")
    @PostMapping(value = "/deletedDict")
    public ResponseResult<Object> deletedDict(@RequestBody @NotEmpty(message = "Dictionary IDs cannot be empty") List<String> ids) {
        dictService.deletedDict(ids);
        return ResponseResult.success();
    }

    /**
     * Query Dictionary Items by Dictionary ID
     * This method retrieves a list of dictionary items associated with the specified dictionary ID.
     *
     * @param dictId The ID of the dictionary to query items for.
     * @return A response containing the list of dictionary items.
     */
    @PreAuthorize("@xz.hasPermission('system:dict:item:list')")
    @GetMapping(value = "/listItemByDictId")
    public ResponseResult<List<DictItemVO>> listItemByDictId(@RequestParam(value = "dictId") String dictId) {
        return ResponseResult.success(dictService.listItemByDictId(dictId));
    }

    /**
     * Create Dictionary Item (Idempotent)
     * This method creates a new dictionary item with the provided information, ensuring idempotency
     * to prevent duplicate submissions.
     *
     * @param dictItemDTO The dictionary item information to be created.
     * @return A response containing the ID of the newly created dictionary item.
     */
    @Idempotent
    @PreAuthorize("@xz.hasPermission('system:dict:item:create')")
    @PostMapping(value = "/creteDictItem")
    public ResponseResult<String> creteDictItem(@RequestBody @Validated(value = CreateGroup.class) DictItemDTO dictItemDTO) {
        return ResponseResult.success(dictService.createDictItem(dictItemDTO));
    }

    /**
     * Update Dictionary Item
     * This method updates the information of an existing dictionary item.
     *
     * @param dictItemDTO The updated dictionary item information.
     * @return A success response indicating the operation was completed.
     */
    @PreAuthorize("@xz.hasPermission('system:dict:item:update')")
    @PostMapping(value = "/updateDictItem")
    public ResponseResult<Object> updateDictItem(@RequestBody @Validated(value = UpdateGroup.class) DictItemDTO dictItemDTO) {
        dictService.updateDictItem(dictItemDTO);
        return ResponseResult.success();
    }

    /**
     * Delete Dictionary Item
     * This method deletes one or more dictionary items based on the provided IDs.
     *
     * @param ids The list of dictionary item IDs to be deleted.
     * @return A success response indicating the operation was completed.
     */
    @PreAuthorize("@xz.hasPermission('system:dict:item:delete')")
    @PostMapping(value = "/deletedDictItem")
    public ResponseResult<Object> deletedDictItem(@RequestBody @NotEmpty(message = "Dictionary item IDs cannot be empty") List<String> ids) {
        dictService.deletedDictItem(ids);
        return ResponseResult.success();
    }
}
