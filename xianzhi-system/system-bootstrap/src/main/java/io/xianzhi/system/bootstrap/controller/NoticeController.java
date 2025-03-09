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

package io.xianzhi.system.bootstrap.controller;

import io.xianzhi.common.idempotent.annotations.Idempotent;
import io.xianzhi.core.result.ListResult;
import io.xianzhi.core.result.ResponseResult;
import io.xianzhi.core.validated.CreateGroup;
import io.xianzhi.core.validated.UpdateGroup;
import io.xianzhi.system.bootstrap.service.NoticeService;
import io.xianzhi.system.model.dto.NoticeDTO;
import io.xianzhi.system.model.page.NoticePage;
import io.xianzhi.system.model.vo.NoticeVO;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 通知公告接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/s/notice")
public class NoticeController {

    /**
     * 公告接口
     */
    private final NoticeService noticeService;


    /**
     * 分页查询公告列表
     *
     * @param noticePage 分页查询参数
     * @return 公告列表
     */
    @PreAuthorize("@xz.hasPermission('system:notice:page')")
    @PostMapping(value = "/pageNoticeList")
    public ResponseResult<ListResult<NoticeVO>> pageNoticeList(NoticePage noticePage) {
        return ResponseResult.success(noticeService.pageNoticeList(noticePage));
    }

    /**
     * 创建公告
     *
     * @param noticeDTO 公告信息
     * @return 创建结果
     */
    @Idempotent
    @PreAuthorize("@xz.hasPermission('system:notice:create')")
    @PostMapping(value = "/createNotice")
    public ResponseResult<String> createNotice(@RequestBody @Validated(value = CreateGroup.class) NoticeDTO noticeDTO) {
        return ResponseResult.success(noticeService.createNotice(noticeDTO));
    }

    /**
     * 更新公告
     *
     * @param noticeDTO 公告信息
     * @return 更新结果
     */
    @PreAuthorize("@xz.hasPermission('system:notice:update')")
    @PostMapping(value = "/updateNotice")
    public ResponseResult<Object> updateNotice(@RequestBody @Validated(value = UpdateGroup.class) NoticeDTO noticeDTO) {
        noticeService.updateNotice(noticeDTO);
        return ResponseResult.success();
    }

    /**
     * 删除公告
     *
     * @param ids 公告ID
     * @return 删除结果
     */
    @PreAuthorize("@xz.hasPermission('system:notice:delete')")
    @PostMapping(value = "/deletedNotice")
    public ResponseResult<Object> deletedNotice(@RequestBody @NotEmpty(message = "公告ID不能为空") List<String> ids) {
        noticeService.deletedNotice(ids);
        return ResponseResult.success();
    }
}
