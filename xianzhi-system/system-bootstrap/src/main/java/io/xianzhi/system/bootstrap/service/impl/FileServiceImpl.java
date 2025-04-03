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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.xianzhi.common.oss.OSSHandler;
import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.exception.BusinessException;
import io.xianzhi.core.result.ListResult;
import io.xianzhi.core.utils.DateUtils;
import io.xianzhi.system.bootstrap.dao.mapper.FileMapper;
import io.xianzhi.system.bootstrap.properties.FileProperties;
import io.xianzhi.system.bootstrap.service.FileService;
import io.xianzhi.system.model.page.FilePage;
import io.xianzhi.system.model.vo.FileVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * 文件接口实现
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    /**
     * OSS处理
     */
    private final OSSHandler ossHandler;

    /**
     * 文件信息持久层
     */
    private final FileMapper fileMapper;

    /**
     * 文件配置
     */
    private final FileProperties fileProperties;

    /**
     * 分页查询文件列表
     *
     * @param filePage 分页查询参数
     * @return 文件列表
     */
    @Override
    public ListResult<FileVO> pageFileList(FilePage filePage) {
        IPage<FileVO> result = fileMapper.pageFileList(new Page<>(filePage.getPageNo(), filePage.getPageSize()), filePage);
        if (ObjectUtils.isEmpty(result.getRecords())) {
            return ListResult.empty();
        }
        return null;
    }

    /**
     * 获取预上传请求
     *
     * @param fileName 文件名称
     * @return 预上传请求地址
     */
    @Override
    public String getPreUploadUrl(String fileName) {
        fileName = checkedFileName(fileName);
        String currentDatePath = DateUtils.getCurrentDatePath();
        return ossHandler.generatePresignedUrlForUpload(fileProperties.getBucketName(), currentDatePath + fileName, Duration.ofHours(1));
    }


    /**
     * 删除文件
     *
     * @param ids 文件ID列表
     */
    @Override
    public void deletedFile(List<String> ids) {

    }


    private String checkedFileName(String fileName) {
        // 找到最后一个点的位置
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex == -1 || lastDotIndex == fileName.length() - 1) {
            throw new BusinessException(CommonCode.ERROR);
        }
        String fileType = fileName.substring(lastDotIndex).toLowerCase();
        if (!fileProperties.getAllowTypes().contains(fileType.toLowerCase(Locale.CHINA))) {
            throw new BusinessException(CommonCode.ERROR.code(), "文件类型不支持");
        }
        return UUID.randomUUID().toString().replace("-", "") + fileType;
    }
}
