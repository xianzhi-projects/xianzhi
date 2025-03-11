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

import io.xianzhi.core.result.ListResult;
import io.xianzhi.core.result.ResponseResult;
import io.xianzhi.system.bootstrap.service.FileService;
import io.xianzhi.system.model.page.FilePage;
import io.xianzhi.system.model.vo.FileVO;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文件接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/s/file")
public class FileController {

    /**
     * 文件接口
     */
    private final FileService fileService;

    /**
     * 分页查询文件列表
     *
     * @param filePage 分页查询参数
     * @return 文件列表
     */
    @PreAuthorize("@xz.hasPermission('system:file:page')")
    @PostMapping(value = "/pageFileList")
    public ResponseResult<ListResult<FileVO>> pageFileList(@RequestBody FilePage filePage) {
        return ResponseResult.success(fileService.pageFileList(filePage));
    }


    /**
     * 删除文件
     *
     * @param ids 文件ID
     * @return 响应信息
     */
    @PreAuthorize("@xz.hasPermission('system:file:delete')")
    @PostMapping(value = "/deletedFile")
    public ResponseResult<Object> deletedFile(@RequestBody @NotEmpty(message = "文件ID不能为空") List<String> ids) {
        return ResponseResult.success();
    }



    @GetMapping(value = "/getPreUploadUrl")
    public ResponseResult<String> getPreUploadUrl() {
        return ResponseResult.success(fileService.getPreUploadUrl());
    }
}
