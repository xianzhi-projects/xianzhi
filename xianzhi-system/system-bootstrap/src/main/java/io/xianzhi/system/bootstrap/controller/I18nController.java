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
import io.xianzhi.system.bootstrap.service.I18nService;
import io.xianzhi.system.model.dto.I18nDTO;
import io.xianzhi.system.model.dto.I18nItemDTO;
import io.xianzhi.system.model.page.I18nPage;
import io.xianzhi.system.model.vo.I18nItemVO;
import io.xianzhi.system.model.vo.I18nVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 国际化接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/s/i18n")
public class I18nController {

    /**
     * 国际化接口
     */
    private final I18nService i18nService;

    /**
     * 分页查询国际化列表
     *
     * @param i18nPage 查询条件
     * @return 国际化列表
     */
    @PreAuthorize("@xz.hasPermission('system:i18n:list')")
    @PostMapping(value = "/pageI18nList")
    public ResponseResult<ListResult<I18nVO>> pageI18nList(@RequestBody I18nPage i18nPage) {
        return ResponseResult.success(i18nService.pageI18nList(i18nPage));
    }

    /**
     * 新增国际化
     *
     * @param i18nDTO 国际化对象
     * @return 国际化ID
     */
    @PostMapping(value = "/createI18n")
    public ResponseResult<String> createI18n(@RequestBody @Validated(value = CreateGroup.class) I18nDTO i18nDTO) {
        return ResponseResult.success(i18nService.createI18n(i18nDTO));
    }

    /**
     * 更新国际化
     *
     * @param i18nDTO 国际化对象
     * @return 响应信息
     */
    @PostMapping(value = "/updateI18n")
    public ResponseResult<String> updateI18n(@RequestBody @Validated(value = UpdateGroup.class) I18nDTO i18nDTO) {
        i18nService.updateI18n(i18nDTO);
        return ResponseResult.success();
    }

    /**
     * 删除国际化
     *
     * @param ids 国际化ID列表
     * @return 响应信息
     */
    @PostMapping(value = "/deletedI18n")
    public ResponseResult<Object> deletedI18n(@RequestBody List<String> ids) {
        i18nService.deletedI18n(ids);
        return ResponseResult.success();
    }

    /**
     * 查询国际化项列表
     *
     * @param i18nId 国际化ID
     * @return 国际化项列表
     */
    @GetMapping(value = "/getI18nItemList")
    public ResponseResult<ListResult<I18nItemVO>> getI18nItemList(@RequestParam String i18nId) {
        return ResponseResult.success(i18nService.getI18nItemList(i18nId));
    }

    /**
     * 新增国际化项
     *
     * @param i18nItemDTO 国际化项对象
     * @return 国际化项ID
     */
    @PostMapping(value = "/createI18nItem")
    public ResponseResult<String> createI18nItem(@RequestBody @Validated(value = CreateGroup.class) I18nItemDTO i18nItemDTO) {
        return ResponseResult.success(i18nService.createI18nItem(i18nItemDTO));
    }

    /**
     * 更新国际化项
     *
     * @param i18nItemDTO 国际化项对象
     * @return 响应信息
     */
    @PostMapping(value = "/updateI18nItem")
    public ResponseResult<String> updateI18nItem(@RequestBody @Validated(value = UpdateGroup.class) I18nItemDTO i18nItemDTO) {
        i18nService.updateI18nItem(i18nItemDTO);
        return ResponseResult.success();
    }

    /**
     * 删除国际化项
     *
     * @param ids 国际化项ID列表
     * @return 响应信息
     */
    @PostMapping(value = "/deletedI18nItem")
    public ResponseResult<Object> deletedI18nItem(@RequestBody List<String> ids) {
        i18nService.deletedI18nItem(ids);
        return ResponseResult.success();
    }
}
