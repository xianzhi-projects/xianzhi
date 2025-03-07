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

package io.xianzhi.system.bootstrap.service;

import io.xianzhi.core.result.ListResult;
import io.xianzhi.system.model.dto.NoticeDTO;
import io.xianzhi.system.model.page.NoticePage;
import io.xianzhi.system.model.vo.NoticeVO;

import java.util.List;

/**
 * 公告接口
 *
 * @author Max
 * @since 1.0.0
 */
public interface NoticeService {

    /**
     * 分页查询公告列表
     *
     * @param noticePage 分页查询参数
     * @return 公告列表
     */
    ListResult<NoticeVO> pageNoticeList(NoticePage noticePage);

    /**
     * 创建公告
     *
     * @param noticeDTO 公告信息
     * @return 创建结果
     */
    String createNotice(NoticeDTO noticeDTO);

    /**
     * 更新公告
     *
     * @param noticeDTO 公告信息
     */
    void updateNotice(NoticeDTO noticeDTO);

    /**
     * 删除公告
     *
     * @param ids 公告ID
     */
    void deletedNotice(List<String> ids);
}
