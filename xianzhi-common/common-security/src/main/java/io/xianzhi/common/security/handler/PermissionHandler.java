package io.xianzhi.common.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 权限处理
 *
 * @author Max
 * @since 1.0.0
 */
@Component(value = "xz")
public class PermissionHandler {

    /**
     * 判断当前用户访问的接口是否具有权限
     *
     * @param requiredPermissions 访问接口所需的权限
     * @return 是否可以访问
     */
    public boolean hasPermission(String... requiredPermissions) {
        // 提前检查空或无权限情况
        if (requiredPermissions == null || requiredPermissions.length == 0) {
            return false;
        }

        // 获取认证信息并验证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (authorities == null || authorities.isEmpty()) {
            return false;
        }

        // 转换为用户权限集合，只执行一次转换
        Set<String> userPermissions = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .filter(Objects::nonNull)  // 使用更强的空检查
                .collect(Collectors.toSet());

        // 检查所需权限
        return Arrays.stream(requiredPermissions)
                .filter(Objects::nonNull)  // 检查null而不是空字符串
                .anyMatch(required ->
                        userPermissions.stream()
                                .filter(Objects::nonNull)
                                .anyMatch(userPerm ->
                                        PatternMatchUtils.simpleMatch(userPerm, required))
                );
    }
}
