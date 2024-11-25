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

import io.xianzhi.core.enums.DateUnitEnum;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 日期工具类
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
public class DateUtil {


    /**
     * 默认日期格式化
     *
     * @since 1.0.0
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 默认日期时间格式化
     *
     * @since 1.0.0
     */
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 私有构造方法
     *
     * @since 1.0.0
     */
    private DateUtil() {
    }


    /**
     * 获取当前日期时间字符串,使用默认格式: yyyy-MM-dd HH:mm:ss
     *
     * @return 当前日期时间字符串，格式: yyyy-MM-dd HH:mm:ss
     * @since 1.0.0
     */
    public static String nowDateTime() {
        return formatDate(new Date(), DEFAULT_DATETIME_FORMAT);
    }

    /**
     * Gets the current date as a formatted string.
     *
     * @return Current date string (format: yyyy-MM-dd)
     * @since 1.0.0
     */
    public static String nowDate() {
        return formatDate(new Date(), DEFAULT_DATE_FORMAT);
    }

    /**
     * Formats a Date object into a string with the specified format.
     *
     * @param date   The Date object to format
     * @param format The desired date format
     * @return Formatted date string, or null if date or format is invalid
     * @since 1.0.0
     */
    public static String formatDate(Date date, String format) {
        if (Objects.isNull(date) || Objects.isNull(format) || format.isEmpty()) {
            return null;
        }
        try {
            return new SimpleDateFormat(format).format(date);
        } catch (IllegalArgumentException e) {
            log.error("Invalid date format: {}", format, e);
            return null;
        }
    }

    /**
     * Parses a date string into a Date object with the specified format.
     *
     * @param dateString The date string to parse
     * @param format     The expected date format
     * @return Parsed Date object, or null if input is invalid
     * @throws ParseException If the date string doesn't match the format
     * @since 1.0.0
     */
    public static Date parseDate(String dateString, String format) throws ParseException {
        if (Objects.isNull(dateString) || dateString.isEmpty() || Objects.isNull(format) || format.isEmpty()) {
            return null;
        }
        return new SimpleDateFormat(format).parse(dateString);
    }

    /**
     * Calculates the difference between two dates in days.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the difference in days, or 0 if either date is null
     * @since 1.0.0
     */
    public static long daysBetween(Date startDate, Date endDate) {
        return calculateDifference(startDate, endDate, TimeUnit.DAYS);
    }

    /**
     * Calculates the difference between two dates in hours.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the difference in hours, or 0 if either date is null
     * @since 1.0.0
     */
    public static long hoursBetween(Date startDate, Date endDate) {
        return calculateDifference(startDate, endDate, TimeUnit.HOURS);
    }

    /**
     * Calculates the difference between two dates in minutes.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the difference in minutes, or 0 if either date is null
     * @since 1.0.0
     */
    public static long minutesBetween(Date startDate, Date endDate) {
        return calculateDifference(startDate, endDate, TimeUnit.MINUTES);
    }

    /**
     * Calculates the difference between two dates in years.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the difference in years, or 0 if either date is null
     * @since 1.0.0
     */
    public static int yearsBetween(Date startDate, Date endDate) {
        return (int) calculateChronoDifference(startDate, endDate, ChronoUnit.YEARS);
    }

    /**
     * Calculates the difference between two dates in months.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the difference in months, or 0 if either date is null
     * @since 1.0.0
     */
    public static int monthsBetween(Date startDate, Date endDate) {
        return (int) calculateChronoDifference(startDate, endDate, ChronoUnit.MONTHS);
    }

    /**
     * Helper method to calculate the difference between two dates using {@link TimeUnit}.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @param unit      the time unit for the difference
     * @return the difference in the specified unit, or 0 if either date is null
     * @since 1.0.0
     */
    private static long calculateDifference(Date startDate, Date endDate, TimeUnit unit) {
        if (Objects.isNull(startDate) || Objects.isNull(endDate)) {
            return 0;
        }
        long diffInMillis = endDate.getTime() - startDate.getTime();
        return unit.convert(diffInMillis, TimeUnit.MILLISECONDS);
    }

    /**
     * Helper method to calculate the difference between two dates using {@link ChronoUnit}.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @param unit      the ChronoUnit for the difference
     * @return the difference in the specified unit, or 0 if either date is null
     * @since 1.0.0
     */
    private static long calculateChronoDifference(Date startDate, Date endDate, ChronoUnit unit) {
        if (Objects.isNull(startDate) || Objects.isNull(endDate)) {
            return 0;
        }
        LocalDateTime start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime end = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return unit.between(start, end);
    }


    /**
     * Calculates a new `Date` by adding or subtracting a specified number of days to/from the given `Date`.
     *
     * <p>This method uses `LocalDate` for date manipulation, ensuring time zone consistency.
     * The resulting date is set to the start of the day (00:00:00) in the system's default time zone.</p>
     *
     * @param date The input `Date` object to adjust.
     *             Must not be `null`.
     * @param days The number of days to add (positive) or subtract (negative).
     * @return A new `Date` object representing the adjusted date at the start of the day.
     * @throws IllegalArgumentException If the provided `date` is `null`.
     * @since 1.0.0
     */
    public static Date getDateByOffset(Date date, int days) {
        if (date == null) {
            throw new IllegalArgumentException("Input date must not be null");
        }

        // Convert Date to LocalDate
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Add or subtract days
        LocalDate adjustedDate = localDate.plusDays(days);

        // Convert LocalDate back to Date at the start of the day
        return Date.from(adjustedDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }


    /**
     * Gets the date representing the previous day at the start of the day (00:00:00).
     *
     * <p>This method uses the `LocalDate` class to calculate the previous day based on the current
     * system date and time zone, and converts it to a `Date` object.</p>
     *
     * @return A `Date` object representing the start of the previous day.
     * @since 1.0.0
     */
    public static Date getYesterday() {
        // Get the current date and subtract one day
        LocalDate yesterday = LocalDate.now(ZoneId.systemDefault()).minusDays(1);

        // Convert the LocalDate to Date at the start of the day (00:00:00)
        return Date.from(yesterday.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }


    /**
     * Calculates the difference between two dates based on the specified unit (YEAR, MONTH, DAY, etc.).
     *
     * <p>This method supports calculating the difference in years, months, days, hours, minutes,
     * and seconds. For year and month calculations, it accounts for whether the end date is earlier
     * in the respective year or month to provide precise results.</p>
     *
     * @param startDate the starting date (cannot be null)
     * @param endDate   the ending date (cannot be null)
     * @param unit      the unit of measurement for the difference (YEAR, MONTH, DAY, etc.)
     * @return the difference between the two dates in the specified unit; returns 0 if startDate or
     * endDate is null
     * @throws IllegalArgumentException if the specified unit is not supported
     * @since 1.0.0
     */
    private static int calculateDateDifference(Date startDate, Date endDate, DateUnitEnum unit) {
        if (startDate == null || endDate == null || unit == null) {
            return 0;
        }

        // Handle year and month calculation separately
        if (unit == DateUnitEnum.YEAR || unit == DateUnitEnum.MONTH) {
            Calendar startCalendar = Calendar.getInstance();
            Calendar endCalendar = Calendar.getInstance();
            startCalendar.setTime(startDate);
            endCalendar.setTime(endDate);

            int years = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
            int months = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

            switch (unit) {
                case YEAR:
                    // Adjust if end date is earlier in the year
                    if (endCalendar.get(Calendar.MONTH) < startCalendar.get(Calendar.MONTH)
                            || (endCalendar.get(Calendar.MONTH) == startCalendar.get(Calendar.MONTH)
                            && endCalendar.get(Calendar.DAY_OF_MONTH) < startCalendar.get(Calendar.DAY_OF_MONTH))) {
                        years--;
                    }
                    return years;

                case MONTH:
                    // Calculate total months and adjust if necessary
                    int totalMonths = years * 12 + months;
                    if (endCalendar.get(Calendar.DAY_OF_MONTH) < startCalendar.get(Calendar.DAY_OF_MONTH)) {
                        totalMonths--;
                    }
                    return totalMonths;
            }
        }

        // For smaller units (days, hours, minutes, seconds), use time difference directly
        long diffInMillis = endDate.getTime() - startDate.getTime();
        return switch (unit) {
            case DAY -> (int) (diffInMillis / (1000L * 60 * 60 * 24));
            case HOUR -> (int) (diffInMillis / (1000L * 60 * 60));
            case MINUTE -> (int) (diffInMillis / (1000L * 60));
            case SECOND -> (int) (diffInMillis / 1000L);
            default -> throw new IllegalArgumentException("Unsupported DateUnit: " + unit);
        };
    }
}
