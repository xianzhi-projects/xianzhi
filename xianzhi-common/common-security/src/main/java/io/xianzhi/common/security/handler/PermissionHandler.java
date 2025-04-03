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
 * Permission Handler
 * This class provides functionality for checking whether the currently authenticated user has the
 * necessary permissions to access a specific interface or resource. It integrates with Spring
 * Security to retrieve the user’s authentication details and authorities, and it supports flexible
 * permission matching using pattern-based checks. The class is registered as a Spring component
 * with the name "xz" for dependency injection purposes.
 *
 * @author Max
 * @since 1.0.0
 */
@Component(value = "xz")
public class PermissionHandler {

    /**
     * Check if the Current User Has Required Permissions
     * This method determines whether the currently authenticated user possesses the permissions
     * required to access a specific interface or resource. It takes a variable number of required
     * permissions as input, retrieves the user’s granted authorities from the Spring Security
     * context, and checks if any of the user’s permissions match any of the required permissions
     * using a pattern-matching approach. The method returns false if any preconditions (e.g.,
     * null input, unauthenticated user) are not met.
     *
     * @param requiredPermissions A variable-length array of permission strings required to access
     *                            the interface or resource. Each string may represent a specific
     *                            permission or a pattern (e.g., "user:read", "admin:*").
     * @return true if the user has at least one matching permission; false otherwise (e.g., if
     * the user is not authenticated, has no authorities, or lacks the required permissions).
     */
    public boolean hasPermission(String... requiredPermissions) {
        // Early validation: return false if required permissions are null or empty
        if (requiredPermissions == null || requiredPermissions.length == 0) {
            return false;
        }

        // Retrieve and validate authentication from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        // Retrieve user’s granted authorities and validate
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (authorities == null || authorities.isEmpty()) {
            return false;
        }

        // Convert authorities to a set of permission strings, filtering out null values
        Set<String> userPermissions = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .filter(Objects::nonNull)  // Ensure no null permissions are included
                .collect(Collectors.toSet());

        // Check if any required permission matches any user permission using pattern matching
        return Arrays.stream(requiredPermissions)
                .filter(Objects::nonNull)  // Filter out null required permissions
                .anyMatch(required ->
                        userPermissions.stream()
                                .filter(Objects::nonNull)  // Redundant but kept for consistency
                                .anyMatch(userPerm ->
                                        PatternMatchUtils.simpleMatch(userPerm, required)));
    }
}
