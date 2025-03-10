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
import io.xianzhi.system.bootstrap.dao.dataobj.DepartmentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * 部门信息持久层
 *
 * @author Max
 * @since 1.0.0
 */
@Mapper
public interface DepartmentMapper extends BaseMapper<DepartmentDO> {

    /**
     * 查询所有部门信息
     *
     * @return 所有部门信息
     */
    List<DepartmentDO> selectAllDepartment();


    /**
     * 根据ID查询部门信息
     *
     * @param id 部门ID
     * @return 部门信息
     */
    Optional<DepartmentDO> selectDepartmentById(@Param("id") String id);

    /**
     * 判断部门名称是否存在
     *
     * @param departmentName 部门名称
     * @param id             部门ID
     * @return 是否存在
     */
    boolean existsDepartmentByNameAndIdNot(@Param("departmentName") String departmentName, @Param("id") String id);

    /**
     * 判断部门邮箱是否存在
     * @param departmentEmail 部门邮箱
     * @param id 部门ID
     * @return 是否存在
     */
    boolean existsDepartmentByEmailAndIdNot(@Param("departmentEmail") String departmentEmail, @Param("id") String id);

    /**
     * 判断部门电话是否存在
     * @param departmentPhone 部门电话
     * @param id 部门ID
     * @return 是否存在
     */
    boolean existsDepartmentByPhoneAndIdNot(@Param("departmentPhone") String departmentPhone, @Param("id") String id);

    /**
     * 判断是否存在子部门
     * @param parentId 父部门ID
     * @return  是否存在
     */
    boolean existsByParentId(@Param("parentId") String parentId);

    /**
     * 删除部门
     * @param id 部门ID
     */
    void deletedDepartmentById(@Param("id") String id);
}
