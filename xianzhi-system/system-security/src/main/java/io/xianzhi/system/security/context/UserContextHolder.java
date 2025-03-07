package io.xianzhi.system.security.context;

import io.xianzhi.core.content.ContextHolder;

/**
 * 用户信息上下文
 *
 * @author Max
 * @since 1.0.0
 */
public class UserContextHolder extends ContextHolder {

    /**
     * 获取当前用户ID
     *
     * @return 当前用户ID
     */
    public static String getCurrentUserId() {
        return getContextOrThrow().getUniqueKey();
    }

    /**
     * 是否是否超级管理员
     *
     * @return 是否超级管理员
     */
    public static boolean admin() {
        return getCurrentUserId().equals("-1");
    }


    /**
     * 获取当前用户
     *
     * @return 当前用户
     */
    public static XianZhiOAuth2UserDetails getCurrentUserOrThrow() {
        return (XianZhiOAuth2UserDetails) getContextOrThrow();
    }


    public static void setAnonymousUser() {
        set(() -> "anonymous");
    }
}
