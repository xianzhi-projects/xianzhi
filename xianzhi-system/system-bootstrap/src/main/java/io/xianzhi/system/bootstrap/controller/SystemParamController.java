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
import io.xianzhi.system.bootstrap.service.SystemParamService;
import io.xianzhi.system.model.dto.SystemParamDTO;
import io.xianzhi.system.model.page.SystemParamPage;
import io.xianzhi.system.model.vo.SystemParamVO;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统参数接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/s/system_param")
public class SystemParamController {

    /**
     * 系统参数服务
     */
    private final SystemParamService systemParamService;

    /**
     * 分页查询系统参数
     *
     * @param systemParamPage 系统参数查询条件
     * @return 系统参数列表
     */
    @PreAuthorize("@xz.hasPermission('sys:system_param:page')")
    @PostMapping(value = "/pageSystemParamList")
    public ResponseResult<ListResult<SystemParamVO>> pageSystemParamList(@RequestBody SystemParamPage systemParamPage) {
        return ResponseResult.success(systemParamService.pageSystemParamList(systemParamPage));
    }

    /**
     * 创建系统参数
     *
     * @param systemParamDTO 系统参数入参
     * @return 系统参数ID
     */
    @Idempotent
    @PreAuthorize("@xz.hasPermission('sys:system_param:create')")
    @PostMapping(value = "/createSystemParam")
    public ResponseResult<String> createSystemParam(@RequestBody @Validated(value = CreateGroup.class) SystemParamDTO systemParamDTO) {
        return ResponseResult.success(systemParamService.createSystemParam(systemParamDTO));
    }

    /**
     * 更新系统参数
     *
     * @param systemParamDTO 系统参数入参
     * @return 空
     */
    @PreAuthorize("@xz.hasPermission('sys:system_param:update')")
    @PostMapping(value = "/updateSystemParam")
    public ResponseResult<Object> updateSystemParam(@RequestBody @Validated(value = UpdateGroup.class) SystemParamDTO systemParamDTO) {
        systemParamService.updateSystemParam(systemParamDTO);
        return ResponseResult.success();
    }

    /**
     * 删除系统参数
     *
     * @param ids 系统参数ID列表
     * @return 空
     */
    @PreAuthorize("@xz.hasPermission('sys:system_param:delete')")
    @PostMapping(value = "/deleteSystemParam")
    public ResponseResult<Object> deleteSystemParam(@RequestBody @NotEmpty(message = "参数ID不能为空") List<String> ids) {
        systemParamService.deleteSystemParam(ids);
        return ResponseResult.success();
    }
}
