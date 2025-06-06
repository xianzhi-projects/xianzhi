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

import io.xianzhi.common.oss.PreUploadUrlVO;
import io.xianzhi.core.result.ListResult;
import io.xianzhi.system.model.page.FilePage;
import io.xianzhi.system.model.vo.FileVO;

import java.util.List;

/**
 * 文件接口
 *
 * @author Max
 * @since 1.0.0
 */
public interface FileService {
    /**
     * 分页查询文件列表
     *
     * @param filePage 分页查询参数
     * @return 文件列表
     */
    ListResult<FileVO> pageFileList(FilePage filePage);

    /**
     * 获取预上传请求
     *
     * @param fileName 文件名称
     * @return 预上传请求地址
     */
    PreUploadUrlVO getPreUploadUrl(String fileName);

    /**
     * 删除文件
     *
     * @param ids 文件ID列表
     */
    void deletedFile(List<String> ids);

    /**
     * 上传文件回调
     *
     * @param objectKey 对象的唯一标识
     * @return 文件信息
     */
    FileVO uploadCallback(String objectKey);
}
