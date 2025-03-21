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
import io.xianzhi.system.bootstrap.dao.dataobj.SystemParamDO;
import io.xianzhi.system.model.page.SystemParamPage;
import io.xianzhi.system.model.vo.SystemParamVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * 系统参数持久层
 *
 * @author Max
 * @since 1.0.0
 */
@Mapper
public interface SystemParamMapper extends BaseMapper<SystemParamDO> {


    /**
     * 根据ID查询系统参数
     *
     * @param id 系统参数ID
     * @return 系统参数
     */
    Optional<SystemParamDO> selectSystemParamById(@Param("id") String id);

    /**
     * 根据参数编码查询系统参数
     *
     * @param paramCode 参数编码
     * @return 系统参数
     */
    Optional<SystemParamDO> selectSystemParamByParamCode(@Param("paramCode") String paramCode);

    /**
     * 判断参数编码是否存在
     *
     * @param paramCode 参数编码
     * @return 是否存在
     */
    boolean existParamByCode(@Param("paramCode") String paramCode);

    /**
     * 判断参数名称是否存在
     *
     * @param paramName 参数名称
     * @param id        系统参数ID
     * @return 是否存在
     */
    boolean existParamByNameAndIdNot(@Param("paramName") String paramName, @Param("id") String id);


    /**
     * 根据ID列表查询系统参数
     *
     * @param ids 系统参数ID列表
     */
    void deletedSystemByIds(@Param("ids") List<String> ids);

    /**
     * 分页查询系统参数
     *
     * @param page            分页条件
     * @param systemParamPage 系统参数查询条件
     * @return 系统参数列表
     */
    IPage<SystemParamVO> pageSystemParamList(Page<SystemParamVO> page, @Param("systemParamPage") SystemParamPage systemParamPage);
}
