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

import java.util.regex.Pattern;

/**
 * Utility class for regular expression validations.
 * This class provides various methods to validate common formats such as email, phone number, ID card number, etc.
 *
 * @author Max
 * @since 1.0.0
 */
public class RegUtil {

    /**
     * Regular expression for validating email addresses.
     * @since 1.0.0
     */
    public static final String EMAIL_REGEX = "^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$";

    /**
     * Regular expression for validating phone numbers.
     * @since 1.0.0
     */
    public static final String PHONE_REGEX = "^1[3-9]\\d{9}$";

    /**
     * Regular expression for validating ID card numbers.
     * @since 1.0.0
     */
    public static final String ID_CARD_REGEX = "(^\\d{15}$)|(^\\d{17}([0-9]|X|x)$)";

    /**
     * Regular expression for validating vehicle license plate numbers.
     * @since 1.0.0
     */
    public static final String LICENSE_PLATE_REGEX = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼港澳使领][A-Z][A-Z0-9]{5}$";

    /**
     * Regular expression for validating password strength (at least 8-30 characters, with upper and lower case letters and numbers).
     * @since 1.0.0
     */
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,30}$";

    /**
     * Regular expression for validating IP addresses.
     * @since 1.0.0
     */
    public static final String IP_ADDRESS_REGEX = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."
            + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."
            + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    /**
     * Private constructor to prevent instantiation of this utility class.
     * @since 1.0.0
     */
    private RegUtil() {
    }

    /**
     * Validates an IP address using the defined regular expression.
     *
     * @param ipAddress The IP address to validate.
     * @return true if the IP address is valid, false otherwise.
     * @since 1.0.0
     */
    public static boolean isValidIpAddress(String ipAddress) {
        return null != ipAddress && Pattern.matches(IP_ADDRESS_REGEX, ipAddress);
    }

    /**
     * Validates an email address using the defined regular expression.
     *
     * @param email The email address to validate.
     * @return true if the email is valid, false otherwise.
     * @since 1.0.0
     */
    public static boolean isValidEmail(String email) {
        return null != email && Pattern.matches(EMAIL_REGEX, email);
    }

    /**
     * Validates a phone number using the defined regular expression.
     *
     * @param phone The phone number to validate.
     * @return true if the phone number is valid, false otherwise.
     * @since 1.0.0
     */
    public static boolean isValidPhone(String phone) {
        return null != phone && Pattern.matches(PHONE_REGEX, phone);
    }

    /**
     * Validates an ID card number using the defined regular expression.
     *
     * @param idCard The ID card number to validate.
     * @return true if the ID card number is valid, false otherwise.
     * @since 1.0.0
     */
    public static boolean isValidIdCard(String idCard) {
        return null != idCard && Pattern.matches(ID_CARD_REGEX, idCard);
    }

    /**
     * Validates a vehicle license plate number using the defined regular expression.
     *
     * @param licensePlate The vehicle license plate number to validate.
     * @return true if the license plate number is valid, false otherwise.
     * @since 1.0.0
     */
    public static boolean isValidLicensePlate(String licensePlate) {
        return null != licensePlate && Pattern.matches(LICENSE_PLATE_REGEX, licensePlate);
    }

    /**
     * Validates a password using the defined regular expression (checks for upper and lower case letters, and digits).
     *
     * @param password The password to validate.
     * @return true if the password is valid, false otherwise.
     * @since 1.0.0
     */
    public static boolean isValidPassword(String password) {
        return null != password && Pattern.matches(PASSWORD_REGEX, password);
    }
}
