package io.xianzhi.core.result;

/**
 * 响应结果顶级接口
 *
 * @author Max
 * @since 1.0.0
 */
public interface Result {


    /**
     * 获取自定义状态码
     *
     * @return 自定义状体码
     */
    String code();

    /**
     * 获取自定义提示信息
     *
     * @return 自定义提示信息
     */
    String message();

}
