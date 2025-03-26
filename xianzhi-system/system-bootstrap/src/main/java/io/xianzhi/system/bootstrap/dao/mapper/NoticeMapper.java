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
import io.xianzhi.system.bootstrap.dao.dataobj.NoticeDO;
import io.xianzhi.system.model.page.NoticePage;
import io.xianzhi.system.model.vo.NoticeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * 公告持久层
 *
 * @author Max
 * @since 1.0.0
 */
@Mapper
public interface NoticeMapper extends BaseMapper<NoticeDO> {

    /**
     * 根据ID查询公告信息
     *
     * @param id 公告ID
     * @return 公告信息
     */
    Optional<NoticeDO> selectNoticeById(@Param("id") String id);

    /**
     * 判断公告标题是否存在
     *
     * @param title    公告标题
     * @param category 公告类型
     * @param id       公告ID
     * @return 是否存在
     */
    boolean existsNoticeByTitleAndCategoryAndIdNot(@Param("title") String title,
                                                   @Param("category") String category,
                                                   @Param("id") String id);

    /**
     * 分页查询公告列表
     *
     * @param noticeVOPage 分页查询参数
     * @param noticePage   公告查询条件
     * @return 公告列表
     */
    IPage<NoticeVO> pageNoticeList(Page<NoticeVO> noticeVOPage, @Param("noticePage") NoticePage noticePage);
}
