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
import io.xianzhi.system.bootstrap.service.I18nService;
import io.xianzhi.system.model.page.I18nPage;
import io.xianzhi.system.model.vo.I18nVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
