
package io.xianzhi.common.oauth2.authorization.utils;

import io.xianzhi.common.oauth2.exception.OAuth2Exception;
import io.xianzhi.core.result.Result;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * OAuth2正则工具类
 *
 * @author Max
 * @since 1.0.0
 */
public class OAuth2Assert {
    /**
     * 判断字符串是否合法，如果不合法抛出Oauth2异常
     *
     * @param text   需要判断的字符串
     * @param result 响应信息
     */
    public static void hasText(String text, Result result) {
        if (!StringUtils.hasText(text)) {
            throw new OAuth2Exception(result);
        }
    }

    /**
     * 判断对象是否为空，如果不合法抛出Oauth2异常
     *
     * @param obj    需要判断的对象
     * @param result 响应信息
     */
    public static void notNull(Object obj, Result result) {
        if (null == obj) {
            throw new OAuth2Exception(result);
        }
    }

    /**
     * 判断集合是否为空，如果不合法抛出Oauth2异常
     *
     * @param obj    需要判断的集合
     * @param result 响应信息
     * @param <E>    泛型
     */
    public static <E> void notEmpty(Collection<E> obj, Result result) {
        if (ObjectUtils.isEmpty(obj)) {
            throw new OAuth2Exception(result);
        }
    }

    /**
     * 判断map是否为空
     *
     * @param obj    需要判断的map
     * @param result 响应结果
     * @param <K>    k 泛型
     * @param <V>    v 泛型
     */
    public static <K, V> void notEmpty(Map<K, V> obj, Result result) {
        if (ObjectUtils.isEmpty(obj)) {
            throw new OAuth2Exception(result);
        }
    }


    /**
     * 判断是否true
     *
     * @param flag   需要判断的标记
     * @param result 响应信息
     */
    public static void isTrue(boolean flag, Result result) {
        if (!flag) {
            throw new OAuth2Exception(result);
        }
    }
}
