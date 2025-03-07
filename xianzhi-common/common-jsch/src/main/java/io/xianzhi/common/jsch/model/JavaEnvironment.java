package io.xianzhi.common.jsch.model;

import lombok.Data;

/**
 * java环境
 *
 * @author Max
 * @since 1.0.0
 */
@Data
public class JavaEnvironment {

    /**
     * java版本
     */
    private String version;

    /**
     * java路径
     */
    private String path;

    /**
     * 是openJdk还是其他的
     */
    private String name;




}
