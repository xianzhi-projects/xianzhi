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
 * 日期工具类
 *
 * @author Max
 * @since 1.0.0
 */
public class DateUtils {


    /**
     * 默认日期格式
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 默认日期时间格式
     */
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATETIME_FORMAT_YYYYMMDD = "yyyyMMdd";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT);
    public static final DateTimeFormatter DATETIME_FORMATTER_YYYYMMDD = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT_YYYYMMDD);

    /**
     * 私有化构造器，防止被实例化
     */
    private DateUtils() {

    }

    /**
     * 将字符串解析为LocalDate对象
     *
     * @param dateString 日期字符串
     * @return 解析后的LocalDate对象
     */
    public static LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString, DATE_FORMATTER);
    }

    /**
     * 将字符串解析为LocalDateTime对象
     *
     * @param dateTimeString 日期时间字符串
     * @return 解析后的LocalDateTime对象
     */
    public static LocalDateTime parseDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, DATETIME_FORMATTER);
    }


    /**
     * 将LocalDate对象格式化为字符串
     *
     * @param date 要格式化的LocalDate对象
     * @return 格式化后的日期字符串
     */
    public static String formatDate(LocalDate date) {
        return DATE_FORMATTER.format(date);
    }

    /**
     * 将LocalDateTime对象格式化为字符串
     *
     * @param dateTime 要格式化的LocalDateTime对象
     * @return 格式化后的日期时间字符串
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return DATETIME_FORMATTER.format(dateTime);
    }


    public static String formatDateTimeYYYYMMDD(LocalDate localDate) {
        return DATETIME_FORMATTER_YYYYMMDD.format(localDate);
    }

    /**
     * 获取当前日期的字符串表示
     *
     * @return 当前日期字符串（格式：yyyy-MM-dd）
     */
    public static String date() {
        return formatDate(LocalDate.now());
    }

    /**
     * 获取当前日期时间的字符串表示
     *
     * @return 当前日期时间字符串（格式：yyyy-MM-dd HH:mm:ss）
     */
    public static String dateTime() {
        return formatDateTime(LocalDateTime.now());
    }

    /**
     * 获取当前时间字符串
     */
    public static String time() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }


    /**
     * 获取当前日期
     *
     * @return 当前日期的LocalDate对象
     */
    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    /**
     * 获取当前日期时间
     *
     * @return 当前日期时间的LocalDateTime对象
     */
    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 计算两个日期之间的天数差异
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 两个日期之间的天数差异
     */
    public static long daysBetween(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    /**
     * 计算两个日期时间之间的秒数差异
     *
     * @param startDateTime 开始日期时间
     * @param endDateTime   结束日期时间
     * @return 两个日期时间之间的秒数差异
     */
    public static long secondsBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return ChronoUnit.SECONDS.between(startDateTime, endDateTime);
    }

    /**
     * 将Date对象转换为LocalDateTime对象
     *
     * @param date 要转换的Date对象
     * @return 转换后的LocalDateTime对象
     */
    public static LocalDateTime convertToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 将LocalDateTime对象转换为Date对象
     *
     * @param localDateTime 要转换的LocalDateTime对象
     * @return 转换后的Date对象
     */
    public static Date convertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取指定日期加上一定天数后的日期
     *
     * @param date 原始日期
     * @param days 需要增加的天数
     * @return 新的日期
     */
    public static LocalDate plusDays(LocalDate date, long days) {
        return date.plusDays(days);
    }

    /**
     * 获取指定日期减去一定天数后的日期
     *
     * @param date 原始日期
     * @param days 需要减少的天数
     * @return 新的日期
     */
    public static LocalDate minusDays(LocalDate date, long days) {
        return date.minusDays(days);
    }

    /**
     * 获取指定日期时间加上一定小时数后的时间
     *
     * @param dateTime 原始日期时间
     * @param hours    需要增加的小时数
     * @return 新的日期时间
     */
    public static LocalDateTime plusHours(LocalDateTime dateTime, long hours) {
        return dateTime.plusHours(hours);
    }

    /**
     * 获取指定日期时间减去一定小时数后的时间
     *
     * @param dateTime 原始日期时间
     * @param hours    需要减少的小时数
     * @return 新的日期时间
     */
    public static LocalDateTime minusHours(LocalDateTime dateTime, long hours) {
        return dateTime.minusHours(hours);
    }


}
