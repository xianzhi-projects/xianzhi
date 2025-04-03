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

package io.xianzhi.common.oss;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 预上传文件地址出参
 *
 * @author Max
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreUploadUrlVO implements Serializable {

    /**
     * 桶名称
     */
    private String bucketName;
    /**
     * 对象唯一标识
     */
    private String objectKey;
    /**
     * 预上传地址
     */
    private String uploadUrl;
}
