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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.exception.BusinessException;
import io.xianzhi.core.result.ListResult;
import io.xianzhi.system.bootstrap.dao.dataobj.UserDO;
import io.xianzhi.system.bootstrap.dao.dataobj.UserDetailsDO;
import io.xianzhi.system.bootstrap.dao.mapper.DepartmentMapper;
import io.xianzhi.system.bootstrap.dao.mapper.UserMapper;
import io.xianzhi.system.bootstrap.service.UserService;
import io.xianzhi.system.model.dto.UserDTO;
import io.xianzhi.system.model.page.UserPage;
import io.xianzhi.system.model.vo.UserVO;
import io.xianzhi.system.security.context.UserContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
     * 部门信息持久层
     */
    private final DepartmentMapper departmentMapper;
    /**
     * 密码加密
     */
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 分页查询用户列表
     *
     * @param userPage 分页查询参数
     * @return 用户列表
     */
    @Override
    public ListResult<UserVO> pageUserList(UserPage userPage) {
        IPage<UserVO> result = userMapper.pageUserList(new Page<>(userPage.getPageNo(), userPage.getPageSize()), userPage);
        if (result.getRecords().isEmpty()) {
            return ListResult.empty();
        }
        return ListResult.of(result.getRecords(), result.getTotal());
    }

    /**
     * 新增用户
     *
     * @param userDTO 用户信息入参
     * @return 用户ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createUser(UserDTO userDTO) {
        UserDO user = checkedUserDTO(userDTO);
        userMapper.insert(user);
        UserDetailsDO userDetailsDO = new UserDetailsDO();
        userDetailsDO.setUserId(user.getId());
        return user.getId();
    }

    /**
     * 更新用户
     *
     * @param userDTO 用户信息入参
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UserDTO userDTO) {
        UserDO userDO = checkedUserDTO(userDTO);
        userMapper.updateById(userDO);

    }

    /**
     * 删除用户
     *
     * @param ids 用户ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletedUser(List<String> ids) {

    }

    /**
     * 获取当前用户信息
     *
     * @return 用户信息
     */
    @Override
    public UserVO getCurrentUserInfo() {
        return userMapper.selectUserInfoById(UserContextHolder.getCurrentUserId()).orElseThrow(() -> new BusinessException(CommonCode.DATA_NOT_EXISTS.code(), "sys.user.not.exists"));
    }

    /**
     * 查询用户详情
     *
     * @param userId 用户ID
     * @return 用户详情
     */
    @Override
    public UserVO getUserDetails(String userId) {
        return userMapper.selectUserInfoById(userId).orElseThrow(() -> new BusinessException(CommonCode.DATA_NOT_EXISTS.code(), "sys.user.not.exists"));
    }

    /**
     * 检查用户信息入参
     *
     * @param userDTO 用户信息入参
     * @return 用户信息实体
     */
    private UserDO checkedUserDTO(UserDTO userDTO) {
        UserDO user;
        if (StringUtils.hasText(userDTO.getId())) {
            user = userMapper.selectUserById(userDTO.getId()).orElseThrow(() -> new BusinessException(CommonCode.DATA_NOT_EXISTS.code(), "sys.user.not.exists"));
        } else {
            user = new UserDO();
            if (userMapper.existsUserByUsername(userDTO.getUsername())) {
                throw new BusinessException(CommonCode.DATA_EXISTS.code(), "sys.user.username.exists");
            }
            user.setUsername(userDTO.getUsername());
        }
        // 检查部门是否存在
        departmentMapper.selectDepartmentById(userDTO.getDepartmentId()).orElseThrow(() -> new BusinessException(CommonCode.DATA_NOT_EXISTS.code(), "sys.department.not.exists"));
        // 检查邮箱是否存在
        if (userMapper.existsUserByEmailAndIdNot(userDTO.getEmail(), user.getId())) {
            throw new BusinessException(CommonCode.DATA_EXISTS.code(), "sys.user.email.exists");
        }
        // 检查手机号码是否存在
        if (StringUtils.hasText(userDTO.getPhone()) && userMapper.existsUserByPhoneAndIdNot(userDTO.getPhone(), user.getId())) {
            throw new BusinessException(CommonCode.DATA_EXISTS.code(), "sys.user.phone.exists");
        }
        user.setUsername(userDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setDepartmentId(userDTO.getDepartmentId());
        return user;
    }


}
