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

package io.xianzhi.common.jsch.model;

import lombok.Data;

/**
 * 系统信息
 *
 * @author Max
 * @since 1.0.0
 */
@Data
public class OsInfo {

    /**
     * 操作系统平台 (Linux, Darwin)
     */
    private String osPlatform;
    /**
     * 发行版名称 (Ubuntu, CentOS, MacOS)
     */
    private String osName;
    /**
     * 版本号
     */
    private String osVersion;
    /**
     * CPU架构 (x86_64, arm64)
     */
    private String osArch;
    /**
     * 详细描述 (Ubuntu 20.04 LTS)
     */
    private String osDescription;
    /**
     * 语言 (en_US.UTF-8)
     */
    private String osLanguage;
    /**
     * 国家代码 (US, CN)
     */
    private String osCountry;
    /**
     * 时区 (UTC, CST)
     */
    private String osTimezone;
}
