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
import io.xianzhi.system.bootstrap.dao.dataobj.I18nDO;
import io.xianzhi.system.model.page.I18nPage;
import io.xianzhi.system.model.vo.I18nVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 国际化持久层
 *
 * @author Max
 * @since 1.0.0
 */
@Mapper
public interface I18nMapper extends BaseMapper<I18nDO> {
    /**
     * 分页查询国际化列表
     *
     * @param page     分页条件
     * @param i18nPage 查询条件
     * @return 国际化列表
     */
    IPage<I18nVO> pageI18nList(Page<I18nVO> page, @Param("i18nPage") I18nPage i18nPage);
}
