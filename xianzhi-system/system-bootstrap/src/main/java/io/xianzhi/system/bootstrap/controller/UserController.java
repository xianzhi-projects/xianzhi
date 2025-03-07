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
import io.xianzhi.system.bootstrap.service.UserService;
import io.xianzhi.system.model.dto.UserDTO;
import io.xianzhi.system.model.page.UserPage;
import io.xianzhi.system.model.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/s/user")
public class UserController {
    /**
     * 用户接口
     */
    private final UserService userService;


    /**
     * 分页查询用户列表
     *
     * @param userPage 分页查询参数
     * @return 用户列表
     */
    @PreAuthorize("@xz.hasPermission('system:user:list')")
    @PostMapping(value = "/pageUserList")
    public ResponseResult<ListResult<UserVO>> pageUserList(@RequestBody UserPage userPage) {
        return ResponseResult.success(userService.pageUserList(userPage));
    }

    /**
     * 新增用户
     *
     * @param userDTO 用户信息入参
     * @return 用户ID
     */
    @Idempotent
    @PreAuthorize("@xz.hasPermission('system:user:create')")
    @PostMapping(value = "/createUser")
    public ResponseResult<String> createUser(@RequestBody @Validated(value = CreateGroup.class) UserDTO userDTO) {
        return ResponseResult.success(userService.createUser(userDTO));
    }

    /**
     * 更新用户
     *
     * @param userDTO 用户信息入参
     * @return 响应信息
     */
    @PreAuthorize("@xz.hasPermission('system:user:update')")
    @PostMapping(value = "/updateUser")
    public ResponseResult<Object> updateUser(@RequestBody @Validated(value = UpdateGroup.class) UserDTO userDTO) {
        userService.updateUser(userDTO);
        return ResponseResult.success();
    }

    /**
     * 删除用户
     *
     * @param ids 用户ID
     * @return 响应信息
     */
    @PreAuthorize("@xz.hasPermission('system:user:delete')")
    @PostMapping(value = "/deletedUser")
    public ResponseResult<Object> deletedUser(@RequestBody List<String> ids) {
        userService.deletedUser(ids);
        return ResponseResult.success();
    }
}
