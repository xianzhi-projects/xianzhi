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
import io.xianzhi.system.bootstrap.dao.dataobj.DictItemDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * 字典项目持久层
 *
 * @author Max
 * @since 1.0.0
 */
@Mapper
public interface DictItemMapper extends BaseMapper<DictItemDO> {


    /**
     * 根据ID查询字典项目信息
     *
     * @param id 字典项目ID
     * @return 字典项目信息
     */
    Optional<DictItemDO> selectDictItemById(@Param("id") String id);


    /**
     * 判断字典项 名称是否存在
     *
     * @param dictId   字典ID
     * @param itemName 字典项名称
     * @param id       字典项ID
     * @return 是否存在
     */
    boolean existsDictItemByDictIdAndItemNameAndIdNot(@Param("dictId") String dictId, @Param("itemName") String itemName, @Param("id") String id);

    /**
     * 根据ID删除字典项
     *
     * @param ids 字典项ID
     */
    void deletedDictItem(@Param("ids") List<String> ids);
}
