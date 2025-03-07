package io.xianzhi.common.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
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
        if (requiredPermissions == null || requiredPermissions.length == 0) {
            return false; // 无需权限时直接拒绝
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getAuthorities() == null) {
            return false; // 当前用户未认证时直接拒绝
        }

        // 将用户权限转换为集合以提高查询效率
        Set<String> userPermissions = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(StringUtils::hasText)
                .collect(Collectors.toSet());

        // 检查是否匹配任一所需权限
        return Arrays.stream(requiredPermissions)
                .filter(StringUtils::hasText) // 过滤掉空白权限
                .anyMatch(requiredPermission ->
                        userPermissions.stream()
                                .anyMatch(userPermission -> PatternMatchUtils.simpleMatch(userPermission, requiredPermission))
                );
    }
}
