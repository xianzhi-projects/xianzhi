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

package io.xianzhi.system.bootstrap.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.xianzhi.system.bootstrap.dao.dataobj.UserDO;
import io.xianzhi.system.model.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * 用户信息持久层
 *
 * @author Max
 * @since 1.0.0
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {

    /**
     * 根据ID查询用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    Optional<UserDO> selectUserById(@Param("id") String id);

    /**
     * 判断部门ID是否绑定用户
     *
     * @param departmentId 部门ID
     * @return 是否绑定
     */
    boolean existsByDepartmentId(@Param("departmentId") String departmentId);


    /**
     * 根据用户名/邮箱/手机号码查询用户信息
     *
     * @param username 用户名/邮箱/手机号码
     * @return 用户信息
     */
    Optional<UserDO> selectUserByUsername(@Param("username") String username);

    /**
     * 查询简单用户列表，仅包含ID nickName, realName, 用户状态，头像地址
     *
     * @param userIds 用户ID
     * @return 用户列表
     */
    List<UserVO> selectSimpleUserList(@Param("userIds") List<String> userIds);




}
