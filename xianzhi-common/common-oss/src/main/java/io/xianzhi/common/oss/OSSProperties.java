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

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * OSS配置类
 *
 * @author Max
 * @since 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "xianzhi.oss")
public class OSSProperties {

    /**
     * 访问ID
     */
    private String accessKey;
    /**
     * 访问密钥
     */
    private String accessSecret;
    /**
     * 存储空间
     */
    private String endpoint;

    /**
     * 区域
     */
    private String region = "us-east-1";



    /**
     * 是否使用路径样式访问
     */
    private Boolean pathStyleAccess = true;


}
