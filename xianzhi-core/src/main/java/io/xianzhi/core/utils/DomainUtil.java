/*
 * Copyright 2024 XianZhi Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.xianzhi.core.utils;

import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for extracting the main domain and subdomains from a given URL.
 *
 * <p>This class provides methods to parse a URL and retrieve the main domain
 * (e.g., example.com) as well as any subdomains included in the URL.
 * It is designed to handle standard URLs and can throw exceptions for invalid inputs.</p>
 *
 * <p>Example:</p>
 * <pre>
 *     Input: https://sub1.sub2.example.com/path
 *     Output: [example.com, sub2.example.com, sub1.sub2.example.com]
 * </pre>
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
public class DomainUtil {


    /**
     * Private constructor to prevent instantiation of this utility class.
     *
     * @since 1.0.0
     */
    private DomainUtil() {
    }

    /**
     * Extracts the main domain and its subdomains from the given URL.
     *
     * @param urlString the full URL (e.g., "https://sub.example.com/path")
     * @return A list containing the main domain and subdomains, ordered from main domain to full subdomain.
     * @throws BusinessException if the URL is invalid or cannot be parsed.
     * @since 1.0.0
     */
    public static List<String> getDomainAndSubdomains(String urlString) {
        List<String> domains = new ArrayList<>();
        try {
            // Parse the URL
            URL url = new URL(urlString);
            String host = url.getHost();

            // Split the host by dots
            String[] parts = host.split("\\.");
            if (parts.length < 2) {
                throw new IllegalArgumentException("Invalid domain: " + host);
            }

            // Get the main domain (e.g., example.com)
            String mainDomain = parts[parts.length - 2] + "." + parts[parts.length - 1];
            domains.add(mainDomain);

            // Add subdomains if they exist
            for (int i = 0; i < parts.length - 2; i++) {
                StringBuilder subdomain = new StringBuilder();
                for (int j = i; j < parts.length; j++) {
                    subdomain.append(parts[j]);
                    if (j < parts.length - 1) {
                        subdomain.append(".");
                    }
                }
                domains.add(subdomain.toString());
            }

        } catch (MalformedURLException e) {
            log.error("Invalid URL: {}", urlString, e);
            throw new BusinessException(CommonCode.PARAMS_CHECK_FAIL);
        }

        return domains;
    }
}
