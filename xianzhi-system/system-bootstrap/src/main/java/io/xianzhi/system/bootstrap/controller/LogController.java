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
import io.xianzhi.system.bootstrap.service.LogService;
import io.xianzhi.system.model.page.LoginLogPage;
import io.xianzhi.system.model.page.OperationLogPage;
import io.xianzhi.system.model.vo.LoginLogVO;
import io.xianzhi.system.model.vo.OperationLogVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 日志接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/s/log")
public class LogController {

    /**
     * 日志接口
     */
    private final LogService logService;


    /**
     * 分页查询操作日志列表
     * @param operationLogPage 操作日志查询条件
     * @return 操作日志列表
     */
    @PreAuthorize("@xz.hasPermission('system:log:pageOperationLogList')")
    @PostMapping(value = "/pageOperationLogList")
    public ResponseResult<ListResult<OperationLogVO>> pageOperationLogList(@RequestBody OperationLogPage operationLogPage){
        return ResponseResult.success(logService.pageOperationLogList(operationLogPage));
    }

    /**
     * 查询操作日志详情
     * @param id 操作日志ID
     * @return 操作日志详情
     */
    @PreAuthorize("@xz.hasPermission('system:log:getOperationLogDetails')")
    @GetMapping(value = "/getOperationLogDetails")
    public ResponseResult<OperationLogVO> getOperationLogDetails(@RequestParam String id){
        return ResponseResult.success(logService.getOperationLogDetails(id));
    }


    /**
     * 分页查询登录日志列表
     * @param loginLogPage 登录日志查询条件
     * @return 登录日志列表
     */
    @PreAuthorize("@xz.hasPermission('system:log:pageLoginLogList')")
    @PostMapping(value = "/pageLoginLogList")
    public ResponseResult<ListResult<LoginLogVO>> pageLoginLogList(@RequestBody LoginLogPage loginLogPage){
        return ResponseResult.success(logService.pageLoginLogList(loginLogPage));
    }

    /**
     * 查询登录日志详情
     * @param id 登录日志ID
     * @return 登录日志详情
     */
    @PreAuthorize("@xz.hasPermission('system:log:getLoginLogDetails')")
    @GetMapping(value = "/getLoginLogDetails")
    public ResponseResult<LoginLogVO> getLoginLogDetails(String id){
        return ResponseResult.success(logService.getLoginLogDetails(id));
    }
}
