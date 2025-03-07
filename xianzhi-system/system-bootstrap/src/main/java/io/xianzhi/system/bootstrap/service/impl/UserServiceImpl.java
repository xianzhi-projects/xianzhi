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

package io.xianzhi.system.bootstrap.service.impl;

import io.xianzhi.core.result.ListResult;
import io.xianzhi.system.bootstrap.dao.mapper.UserMapper;
import io.xianzhi.system.bootstrap.service.UserService;
import io.xianzhi.system.model.dto.UserDTO;
import io.xianzhi.system.model.page.UserPage;
import io.xianzhi.system.model.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户接口实现
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    /**
     * 用户信息持久层
     */
    private final UserMapper userMapper;

    /**
     * 分页查询用户列表
     *
     * @param userPage 分页查询参数
     * @return 用户列表
     */
    @Override
    public ListResult<UserVO> pageUserList(UserPage userPage) {
        return null;
    }

    /**
     * 新增用户
     *
     * @param userDTO 用户信息入参
     * @return 用户ID
     */
    @Override
    public String createUser(UserDTO userDTO) {
        return "";
    }

    /**
     * 更新用户
     *
     * @param userDTO 用户信息入参
     */
    @Override
    public void updateUser(UserDTO userDTO) {

    }

    /**
     * 删除用户
     *
     * @param ids 用户ID
     */
    @Override
    public void deletedUser(List<String> ids) {

    }
}
