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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.xianzhi.system.bootstrap.dao.dataobj.RoleDO;
import io.xianzhi.system.model.page.RolePage;
import io.xianzhi.system.model.vo.RoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 角色持久层
 *
 * @author Max
 * @since 1.0.0
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleDO> {
    /**
     * 分页查询角色列表
     *
     * @param roleVOPage 分页查询参数
     * @param rolePage   角色查询条件
     * @return 角色列表
     */
    IPage<RoleVO> pageRoleList(Page<RoleVO> roleVOPage, @Param("rolePage") RolePage rolePage);
}
