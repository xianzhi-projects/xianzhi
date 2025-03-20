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

package io.xianzhi.code.bootstrap.controller;

import io.xianzhi.code.bootstrap.service.WebHooksService;
import io.xianzhi.code.model.dto.WebHooksDTO;
import io.xianzhi.code.model.page.WebHooksPage;
import io.xianzhi.code.model.vo.WebHooksVO;
import io.xianzhi.common.idempotent.annotations.Idempotent;
import io.xianzhi.core.result.ListResult;
import io.xianzhi.core.result.ResponseResult;
import io.xianzhi.core.validated.CreateGroup;
import io.xianzhi.core.validated.UpdateGroup;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * web钩子接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/c/webHooks")
public class WebHooksController {

    /**
     * 钩子接口
     */
    private final WebHooksService webHooksService;

    /**
     * 分页查询webHooks列表
     *
     * @param webHooksPage 分页查询参数
     * @return webHooks列表
     */
    @PostMapping(value = "/pageWebHooksList")
    public ResponseResult<ListResult<WebHooksVO>> pageWebHooksList(@RequestBody WebHooksPage webHooksPage) {
        return ResponseResult.success();
    }

    /**
     * 创建webHooks  (幂等)
     *
     * @param webHooksDTO webHooks信息入参
     * @return 创建结果
     */
    @Idempotent
    @PostMapping(value = "/createWebHooks")
    public ResponseResult<String> createWebHooks(@RequestBody @Validated(value = CreateGroup.class) WebHooksDTO webHooksDTO) {
        return ResponseResult.success();
    }

    /**
     * 更新webHooks
     *
     * @param webHooksDTO webHooks信息入参
     * @return 更新结果
     */
    @PostMapping(value = "/updateWebHooks")
    public ResponseResult<String> updateWebHooks(@RequestBody @Validated(value = UpdateGroup.class) WebHooksDTO webHooksDTO) {
        return ResponseResult.success();
    }

    /**
     * 删除webHooks
     *
     * @param ids webHooks ID
     * @return 删除结果
     */
    @PostMapping(value = "/deleteWebHooks")
    public ResponseResult<String> deleteWebHooks(@RequestBody @NotEmpty(message = "ID不能为空") List<String> ids) {
        return ResponseResult.success();
    }
}
