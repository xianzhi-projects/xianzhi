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

import io.xianzhi.core.exception.BusinessException;
import io.xianzhi.core.result.ListResult;
import io.xianzhi.system.bootstrap.dao.dataobj.NoticeDO;
import io.xianzhi.system.bootstrap.dao.mapper.NoticeMapper;
import io.xianzhi.system.bootstrap.service.NoticeService;
import io.xianzhi.system.model.dto.NoticeDTO;
import io.xianzhi.system.model.page.NoticePage;
import io.xianzhi.system.model.vo.NoticeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 公告接口实现
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    /**
     * 公告信息持久层
     */
    private final NoticeMapper noticeMapper;

    /**
     * 分页查询公告列表
     *
     * @param noticePage 分页查询参数
     * @return 公告列表
     */
    @Override
    public ListResult<NoticeVO> pageNoticeList(NoticePage noticePage) {
        return null;
    }

    /**
     * 创建公告
     *
     * @param noticeDTO 公告信息
     * @return 创建结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createNotice(NoticeDTO noticeDTO) {
        return "";
    }

    /**
     * 更新公告
     *
     * @param noticeDTO 公告信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateNotice(NoticeDTO noticeDTO) {

    }

    /**
     * 删除公告
     *
     * @param ids 公告ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletedNotice(List<String> ids) {

    }

    /**
     * 检查公告信息入参
     *
     * @param noticeDTO 公告信息入参
     * @return 公告信息
     */
    private NoticeDO checkedNoticeDTO(NoticeDTO noticeDTO) {
        NoticeDO noticeDO;
        if (StringUtils.hasText(noticeDTO.getId())) {
            noticeDO = noticeMapper.selectNoticeById(noticeDTO.getId()).orElseThrow(() -> new BusinessException("公告ID不能为空"));
        } else {
            noticeDO = new NoticeDO();
        }
        if (noticeMapper.existsNoticeByTitleAndCategoryAndIdNot(noticeDTO.getNoticeTitle(), noticeDTO.getNoticeCategory(), noticeDTO.getId())) {
            throw new BusinessException("已经存在相同公告标题");
        }
        return noticeDO;
    }
}
