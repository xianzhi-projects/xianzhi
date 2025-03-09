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
import io.xianzhi.system.bootstrap.dao.dataobj.DictDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * 字典持久层
 *
 * @author Max
 * @since 1.0.0
 */
@Mapper
public interface DictMapper extends BaseMapper<DictDO> {


    /**
     * 根据ID查询字典信息
     *
     * @param id 字典ID
     * @return 字典信息
     */
    Optional<DictDO> selectDictById(@Param("id") String id);

    /**
     * 判断字典编码是否存在
     *
     * @param dictCode 字典编码
     * @return 是否存在
     */
    boolean existsDictByDictCode(@Param("dictCode") String dictCode);

    /**
     * 判断字典名称是否存在
     *
     * @param dictName 字典名称
     * @param id       字典ID
     * @return 是否存在
     */
    boolean existsDictByDictNameAndIdNot(@Param("dictName") String dictName, @Param("id") String id);



}
