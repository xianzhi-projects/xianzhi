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

package io.xianzhi.core.utils;

import java.security.SecureRandom;

/**
 * Utility class for generating verification codes.
 *
 * @author Max
 * @since 1.0.0
 */
public class VerificationCodeUtil {

    /**
     * Private constructor to prevent instantiation of this utility class.
     *
     * @since 1.0.0
     */
    private VerificationCodeUtil() {
    }

    /**
     * Generates a numeric verification code of the specified length.
     * If the length is neither 4 nor 6, the length defaults to 6.
     *
     * @param length The length of the verification code to be generated (4 or 6).
     * @return The generated numeric verification code.
     * @since 1.0.0
     */
    public static String generateNumericCode(int length) {
        if (length != 4 && length != 6) {
            length = 6; // Default to 6 if length is not 4 or 6
        }
        SecureRandom random = new SecureRandom(); // Secure random number generator
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10); // Generate a random digit between 0 and 9
            sb.append(digit);
        }
        return sb.toString(); // Return the generated verification code
    }
}

