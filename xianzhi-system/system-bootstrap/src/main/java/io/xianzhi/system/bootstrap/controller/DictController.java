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
 * 字典接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/s/dict")
public class DictController {

    /**
     * 字典接口
     */
    private final DictService dictService;


    /**
     * 分页查询字典列表
     *
     * @param dictPage 分页查询参数
     * @return 字典列表
     */
    @PreAuthorize("@xz.hasPermission('system:dict:page')")
    @PostMapping(value = "/pageDictList")
    public ResponseResult<ListResult<DictVO>> pageDictList(@RequestBody DictPage dictPage) {
        return ResponseResult.success(dictService.pageDictList(dictPage));
    }

    /**
     * 新增字典信息
     *
     * @param dictDTO 字典信息入参
     * @return 字典ID
     */
    @PreAuthorize("@xz.hasPermission('system:dict:create')")
    @PostMapping(value = "/createDict")
    public ResponseResult<String> createDict(@RequestBody @Validated(value = CreateGroup.class) DictDTO dictDTO) {
        return ResponseResult.success(dictService.createDict(dictDTO));
    }

    /**
     * 更新字典信息
     *
     * @param dictDTO 字典信息入参
     * @return 字典ID
     */
    @PreAuthorize("@xz.hasPermission('system:dict:update')")
    @PostMapping(value = "/updateDict")
    public ResponseResult<Object> updateDict(@RequestBody @Validated(value = UpdateGroup.class) DictDTO dictDTO) {
        dictService.updateDict(dictDTO);
        return ResponseResult.success();
    }

    /**
     * 删除字典
     *
     * @param ids 字典ID
     * @return 响应信息
     */
    @PreAuthorize("@xz.hasPermission('system:dict:delete')")
    @PostMapping(value = "/deletedDict")
    public ResponseResult<Object> deletedDict(@RequestBody @NotEmpty(message = "字典ID不能为空") List<String> ids) {
        dictService.deletedDict(ids);
        return ResponseResult.success();
    }

    /**
     * 根据字典ID查询字典项
     *
     * @param dictId 字典ID
     * @return 字典项信息
     */
    @PreAuthorize("@xz.hasPermission('system:dict:item:list')")
    @GetMapping(value = "/listItemByDictId")
    public ResponseResult<List<DictItemVO>> listItemByDictId(@RequestParam(value = "dictId") String dictId) {
        return ResponseResult.success(dictService.listItemByDictId(dictId));
    }

    /**
     * 新增字典项
     *
     * @param dictItemDTO 字典项信息入参
     * @return 字典项ID
     */
    @PreAuthorize("@xz.hasPermission('system:dict:item:create')")
    @PostMapping(value = "/creteDictItem")
    public ResponseResult<String> creteDictItem(@RequestBody @Validated(value = CreateGroup.class) DictItemDTO dictItemDTO) {
        return ResponseResult.success(dictService.createDictItem(dictItemDTO));
    }

    /**
     * 更新字典项
     *
     * @param dictItemDTO 字典项信息入参
     * @return 字典项ID
     */
    @PreAuthorize("@xz.hasPermission('system:dict:item:update')")
    @PostMapping(value = "/updateDictItem")
    public ResponseResult<Object> updateDictItem(@RequestBody @Validated(value = UpdateGroup.class) DictItemDTO dictItemDTO) {
        dictService.updateDictItem(dictItemDTO);
        return ResponseResult.success();
    }

    /**
     * 删除字典项
     *
     * @param ids 字典项ID
     * @return 响应信息
     */
    @PreAuthorize("@xz.hasPermission('system:dict:item:delete')")
    @PostMapping(value = "/deletedDictItem")
    public ResponseResult<Object> deletedDictItem(@RequestBody @NotEmpty(message = "字典项ID不能为空") List<String> ids) {
        dictService.deletedDictItem(ids);
        return ResponseResult.success();
    }


}
