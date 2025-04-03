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

package io.xianzhi.core.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;


/**
 * Date Utility Class
 * This class provides a collection of static methods and constants for handling date and time
 * operations in a consistent and reusable manner. It leverages the Java 8 Date and Time API
 * (java.time package) to offer functionality such as parsing, formatting, and manipulating dates
 * and times, as well as calculating differences and performing conversions between legacy and
 * modern date-time representations. The class is designed as a utility with a private constructor
 * to prevent instantiation.
 *
 * @author Max
 * @since 1.0.0
 */
public class DateUtils {

    /**
     * Default Date Format
     * A constant defining the default format for dates as "yyyy-MM-dd" (e.g., 2025-04-02). This
     * format is used consistently across methods that handle date-only operations.
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * Default Date-Time Format
     * A constant defining the default format for date-time values as "yyyy-MM-dd HH:mm:ss"
     * (e.g., 2025-04-02 14:30:45). This format includes both date and time components with
     * seconds precision.
     */
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * Alternative Date-Time Format (YYYYMMDD)
     * A constant defining an alternative compact date format as "yyyyMMdd" (e.g., 20250402).
     * This format is useful for scenarios requiring a concise, machine-readable date representation.
     */
    public static final String DEFAULT_DATETIME_FORMAT_YYYYMMDD = "yyyyMMdd";

    /**
     * Date Formatter
     * A pre-configured DateTimeFormatter instance for parsing and formatting dates using the
     * DEFAULT_DATE_FORMAT ("yyyy-MM-dd"). It ensures consistent date string handling.
     */
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);

    /**
     * Date-Time Formatter
     * A pre-configured DateTimeFormatter instance for parsing and formatting date-time values
     * using the DEFAULT_DATETIME_FORMAT ("yyyy-MM-dd HH:mm:ss"). It ensures consistent date-time
     * string handling.
     */
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT);

    /**
     * Alternative Date-Time Formatter (YYYYMMDD)
     * A pre-configured DateTimeFormatter instance for parsing and formatting dates using the
     * DEFAULT_DATETIME_FORMAT_YYYYMMDD ("yyyyMMdd"). It supports compact date representations.
     */
    public static final DateTimeFormatter DATETIME_FORMATTER_YYYYMMDD = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT_YYYYMMDD);

    /**
     * Private Constructor
     * This constructor is private to prevent instantiation of the utility class, as all methods
     * are static and the class is intended solely for utility purposes.
     */
    private DateUtils() {
    }

    /**
     * Parse String to LocalDate
     * This method converts a date string into a LocalDate object using the predefined DATE_FORMATTER.
     * The input string must match the "yyyy-MM-dd" format, or a DateTimeParseException will be thrown.
     *
     * @param dateString The date string to parse (e.g., "2025-04-02").
     * @return A LocalDate object representing the parsed date.
     */
    public static LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString, DATE_FORMATTER);
    }

    /**
     * Parse String to LocalDateTime
     * This method converts a date-time string into a LocalDateTime object using the predefined
     * DATETIME_FORMATTER. The input string must match the "yyyy-MM-dd HH:mm:ss" format, or a
     * DateTimeParseException will be thrown.
     *
     * @param dateTimeString The date-time string to parse (e.g., "2025-04-02 14:30:45").
     * @return A LocalDateTime object representing the parsed date and time.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, DATETIME_FORMATTER);
    }

    /**
     * Format LocalDate to String
     * This method formats a LocalDate object into a string using the predefined DATE_FORMATTER,
     * resulting in a "yyyy-MM-dd" formatted string.
     *
     * @param date The LocalDate object to format.
     * @return A string representing the formatted date (e.g., "2025-04-02").
     */
    public static String formatDate(LocalDate date) {
        return DATE_FORMATTER.format(date);
    }

    /**
     * Format LocalDateTime to String
     * This method formats a LocalDateTime object into a string using the predefined DATETIME_FORMATTER,
     * resulting in a "yyyy-MM-dd HH:mm:ss" formatted string.
     *
     * @param dateTime The LocalDateTime object to format.
     * @return A string representing the formatted date and time (e.g., "2025-04-02 14:30:45").
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return DATETIME_FORMATTER.format(dateTime);
    }

    /**
     * Format LocalDate to YYYYMMDD String
     * This method formats a LocalDate object into a compact string using the predefined
     * DATETIME_FORMATTER_YYYYMMDD, resulting in a "yyyyMMdd" formatted string.
     *
     * @param localDate The LocalDate object to format.
     * @return A string representing the formatted date (e.g., "20250402").
     */
    public static String formatDateTimeYYYYMMDD(LocalDate localDate) {
        return DATETIME_FORMATTER_YYYYMMDD.format(localDate);
    }

    /**
     * Get Current Date as String
     * This method returns the current date as a string in the "yyyy-MM-dd" format by formatting
     * the current LocalDate.
     *
     * @return A string representing the current date (e.g., "2025-04-02").
     */
    public static String date() {
        return formatDate(LocalDate.now());
    }

    /**
     * Get Current Date-Time as String
     * This method returns the current date and time as a string in the "yyyy-MM-dd HH:mm:ss"
     * format by formatting the current LocalDateTime.
     *
     * @return A string representing the current date and time (e.g., "2025-04-02 14:30:45").
     */
    public static String dateTime() {
        return formatDateTime(LocalDateTime.now());
    }

    /**
     * Get Current Time as String
     * This method returns the current time as a string in the "HH:mm:ss" format, excluding the date.
     *
     * @return A string representing the current time (e.g., "14:30:45").
     */
    public static String time() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    /**
     * Get Current Date
     * This method returns the current date as a LocalDate object, based on the system clock in
     * the default time zone.
     *
     * @return A LocalDate object representing the current date.
     */
    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    /**
     * Get Current Date-Time
     * This method returns the current date and time as a LocalDateTime object, based on the system
     * clock in the default time zone.
     *
     * @return A LocalDateTime object representing the current date and time.
     */
    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    /**
     * Calculate Days Between Two Dates
     * This method computes the number of days between two LocalDate objects using ChronoUnit.DAYS.
     * The result is positive if endDate is after startDate, negative if before, or zero if equal.
     *
     * @param startDate The starting date.
     * @param endDate   The ending date.
     * @return The number of days between the two dates.
     */
    public static long daysBetween(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    /**
     * Calculate Seconds Between Two Date-Times
     * This method computes the number of seconds between two LocalDateTime objects using
     * ChronoUnit.SECONDS. The result is positive if endDateTime is after startDateTime, negative
     * if before, or zero if equal.
     *
     * @param startDateTime The starting date-time.
     * @param endDateTime   The ending date-time.
     * @return The number of seconds between the two date-times.
     */
    public static long secondsBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return ChronoUnit.SECONDS.between(startDateTime, endDateTime);
    }

    /**
     * Convert Date to LocalDateTime
     * This method converts a legacy java.util.Date object to a LocalDateTime object using the
     * system default time zone.
     *
     * @param date The Date object to convert.
     * @return A LocalDateTime object representing the same instant as the input Date.
     */
    public static LocalDateTime convertToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * Convert LocalDateTime to Date
     * This method converts a LocalDateTime object to a legacy java.util.Date object using the
     * system default time zone.
     *
     * @param localDateTime The LocalDateTime object to convert.
     * @return A Date object representing the same instant as the input LocalDateTime.
     */
    public static Date convertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Add Days to a Date
     * This method returns a new LocalDate by adding the specified number of days to the given date.
     *
     * @param date The original date.
     * @param days The number of days to add (can be negative to subtract).
     * @return A new LocalDate object with the adjusted date.
     */
    public static LocalDate plusDays(LocalDate date, long days) {
        return date.plusDays(days);
    }

    /**
     * Subtract Days from a Date
     * This method returns a new LocalDate by subtracting the specified number of days from the
     * given date.
     *
     * @param date The original date.
     * @param days The number of days to subtract.
     * @return A new LocalDate object with the adjusted date.
     */
    public static LocalDate minusDays(LocalDate date, long days) {
        return date.minusDays(days);
    }

    /**
     * Add Hours to a Date-Time
     * This method returns a new LocalDateTime by adding the specified number of hours to the
     * given date-time.
     *
     * @param dateTime The original date-time.
     * @param hours    The number of hours to add (can be negative to subtract).
     * @return A new LocalDateTime object with the adjusted date-time.
     */
    public static LocalDateTime plusHours(LocalDateTime dateTime, long hours) {
        return dateTime.plusHours(hours);
    }

    /**
     * Subtract Hours from a Date-Time
     * This method returns a new LocalDateTime by subtracting the specified number of hours from
     * the given date-time.
     *
     * @param dateTime The original date-time.
     * @param hours    The number of hours to subtract.
     * @return A new LocalDateTime object with the adjusted date-time.
     */
    public static LocalDateTime minusHours(LocalDateTime dateTime, long hours) {
        return dateTime.minusHours(hours);
    }
}

