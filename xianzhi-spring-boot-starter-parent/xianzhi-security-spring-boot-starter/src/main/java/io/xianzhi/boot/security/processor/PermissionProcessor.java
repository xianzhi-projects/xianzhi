/*
 *  Copyright 2024 XianZhi Group
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
 *
 */

package io.xianzhi.boot.security.processor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

/**
 * PermissionProcessor is a utility class that checks if the current user has the necessary permissions
 * to access a specific interface or resource.
 *
 * @author Max
 * @since 1.0.0
 */
public class PermissionProcessor {

    /**
     * Checks if the current user has any of the specified permissions.
     *
     * @param permissions An array of permissions required to access a resource.
     * @return {@code true} if the user has at least one of the specified permissions; {@code false} otherwise.
     * @since 1.0.0
     */
    public boolean hasPermission(String... permissions) {
        // Return false if no permissions are provided
        if (permissions == null || permissions.length == 0) {
            return false;
        }

        // Retrieve the current user's authentication details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }

        // Check if the user has at least one matching permission
        return authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .filter(StringUtils::hasText)
                .anyMatch(userPermission ->
                        PatternMatchUtils.simpleMatch(permissions, userPermission));
    }
}