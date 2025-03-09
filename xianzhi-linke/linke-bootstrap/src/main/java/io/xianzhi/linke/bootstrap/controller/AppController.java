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

package io.xianzhi.linke.bootstrap.controller;

import io.xianzhi.core.result.ListResult;
import io.xianzhi.core.result.ResponseResult;
import io.xianzhi.linke.bootstrap.service.AppService;
import io.xianzhi.linke.model.page.AppPage;
import io.xianzhi.linke.model.vo.AppVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 应用接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/app")
public class AppController {

    /**
     * 应用接口
     */
    private final AppService appService;


    /**
     * 分页查询应用列表
     * @param appPage 查询条件
     * @return 应用列表
     */
    @PreAuthorize("@xz.hasPermission('linke:app:list')")
    @PostMapping(value = "/pageAppList")
    public ResponseResult<ListResult<AppVO>> pageAppList(@RequestBody AppPage appPage){
        return ResponseResult.success();
    }


}
