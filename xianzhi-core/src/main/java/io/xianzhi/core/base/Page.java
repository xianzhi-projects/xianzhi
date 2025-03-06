package io.xianzhi.core.base;

import lombok.Data;

import java.io.Serializable;

/**
 * 基础分页
 *
 * @author Max
 * @since 1.0.0
 */
@Data
public class Page implements Serializable {

    /**
     * 当前页码
     */
    private Integer pageNo = 1;
    /**
     * 每页展示条数
     */
    private Integer pageSize = 20;
}
