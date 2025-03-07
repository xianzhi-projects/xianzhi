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
