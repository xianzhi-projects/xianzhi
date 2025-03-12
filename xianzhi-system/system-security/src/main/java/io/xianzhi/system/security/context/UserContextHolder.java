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
        return getCurrentUserOrThrow().getUsername().equals("admin");
    }


    /**
     * 获取当前用户
     *
     * @return 当前用户
     */
    public static XianZhiOAuth2UserDetails getCurrentUserOrThrow() {
        return (XianZhiOAuth2UserDetails) getContextOrThrow();
    }

    /**
     * 设置系统用户，用于定时任务，或者其他无法获取用户信息
     */
    public static void setAnonymousUser() {
        set(() -> "system");
    }
}
