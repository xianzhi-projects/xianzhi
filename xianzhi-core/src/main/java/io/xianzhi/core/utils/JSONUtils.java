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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.exception.BusinessException;
import jakarta.servlet.ServletInputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * JSON Utility Class
 * This class provides static methods for converting between JSON strings and Java objects, leveraging
 * the Jackson library's ObjectMapper. It supports serialization and deserialization of objects,
 * lists, and streams, with custom handling for Java 8 date-time types (LocalDate and LocalDateTime).
 * The class is designed as a utility with a private constructor to prevent instantiation and includes
 * logging for error tracking.
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
public class JSONUtils {

    /**
     * ObjectMapper Instance
     * A pre-configured ObjectMapper used for all JSON serialization and deserialization operations.
     * It is initialized statically with custom settings for date-time handling and BigDecimal formatting.
     */
    public static final ObjectMapper objectMapper;

    /**
     * Date Formatter
     * A DateTimeFormatter for formatting LocalDate objects as "yyyy-MM-dd" (e.g., "2025-04-02").
     */
    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Date-Time Formatter
     * A DateTimeFormatter for formatting LocalDateTime objects as "yyyy-MM-dd HH:mm:ss"
     * (e.g., "2025-04-02 14:30:45").
     */
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    static {
        objectMapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));

        // Register the JavaTimeModule and configure ObjectMapper
        objectMapper.registerModule(module)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Prevent timestamps for dates
        objectMapper.getFactory().configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true); // Plain BigDecimal output
    }

    /**
     * Private Constructor
     * This constructor is private to prevent instantiation of the utility class, as all methods
     * are static and intended for utility use only.
     */
    private JSONUtils() {
    }

    /**
     * Convert Object to JSON String
     * This method serializes a given object into a JSON string using the configured ObjectMapper.
     * If the object is null, it returns null. Exceptions during serialization are logged and
     * wrapped in a BusinessException.
     *
     * @param object The object to serialize into a JSON string.
     * @return A JSON string representation of the object, or null if the input is null.
     * @throws BusinessException If serialization fails, with a generic ERROR status from CommonCode.
     */
    public static String toJSONString(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("Failed to convert object to JSON string", e);
            throw new BusinessException(CommonCode.ERROR);
        }
    }

    /**
     * Parse JSON String to Object
     * This method deserializes a JSON string into an object of the specified class. If the input
     * string is null or empty, it returns null. Exceptions during deserialization are logged and
     * wrapped in a BusinessException.
     *
     * @param json  The JSON string to parse.
     * @param clazz The target class to deserialize the JSON into.
     * @param <R>   The generic type of the returned object.
     * @return An object of type R, or null if the input string is null or empty.
     * @throws BusinessException If deserialization fails, with a generic ERROR status from CommonCode.
     */
    public static <R> R parseObject(String json, Class<R> clazz) {
        if (!StringUtils.hasText(json)) {
            return null;
        }
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error("Failed to parse JSON string to object", e);
            throw new BusinessException(CommonCode.ERROR);
        }
    }

    /**
     * Parse JSON String to List of Objects
     * This method deserializes a JSON string into a List of objects of the specified class. If the
     * input string is null or empty, it returns null. Exceptions during deserialization are logged
     * and wrapped in a BusinessException.
     *
     * @param json  The JSON string representing an array to parse.
     * @param clazz The target class for the list elements.
     * @param <R>   The generic type of the list elements.
     * @return A List of objects of type R, or null if the input string is null or empty.
     * @throws BusinessException If deserialization fails, with a generic ERROR status from CommonCode.
     */
    public static <R> List<R> parseArray(String json, Class<R> clazz) {
        if (!StringUtils.hasText(json)) {
            return null;
        }
        try {
            return objectMapper.readValue(json,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            log.error("Failed to parse JSON string to list of objects", e);
            throw new BusinessException(CommonCode.ERROR);
        }
    }

    /**
     * Convert Object to Another Object
     * This method converts one object to another object of the specified class using Jackson's
     * conversion capabilities. If the input object is null, it returns null. Exceptions during
     * conversion are logged and wrapped in a BusinessException.
     *
     * @param obj   The source object to convert.
     * @param clazz The target class to convert the object into.
     * @param <R>   The generic type of the returned object.
     * @return An object of type R, or null if the input object is null.
     * @throws BusinessException If conversion fails, with a generic ERROR status from CommonCode.
     */
    public static <R> R toObject(Object obj, Class<R> clazz) {
        if (obj == null) {
            return null;
        }
        try {
            return objectMapper.convertValue(obj, clazz);
        } catch (Exception e) {
            log.error("Failed to convert object to another object", e);
            throw new BusinessException(CommonCode.ERROR);
        }
    }

    /**
     * Convert Object to List of Objects
     * This method converts an object (typically a collection or array-like structure) to a List
     * of objects of the specified class. If the input object is null or empty, it returns null.
     * Exceptions during conversion are logged and wrapped in a BusinessException.
     *
     * @param obj   The source object to convert.
     * @param clazz The target class for the list elements.
     * @param <R>   The generic type of the list elements.
     * @return A List of objects of type R, or null if the input object is null or empty.
     * @throws BusinessException If conversion fails, with a generic ERROR status from CommonCode.
     */
    public static <R> List<R> toObjectList(Object obj, Class<R> clazz) {
        if (ObjectUtils.isEmpty(obj)) {
            return null;
        }
        try {
            return objectMapper.convertValue(obj,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            log.error("Failed to convert object to list of objects", e);
            throw new BusinessException(CommonCode.ERROR);
        }
    }

    /**
     * Parse Input Stream to JsonNode
     * This method reads a ServletInputStream and converts it into a JsonNode object, which can
     * represent any JSON structure (object, array, etc.). Exceptions during parsing are logged
     * and wrapped in a BusinessException.
     *
     * @param inputStream The ServletInputStream containing JSON data.
     * @return A JsonNode object representing the parsed JSON structure.
     * @throws BusinessException If parsing fails, with a generic ERROR status from CommonCode.
     */
    public static JsonNode parseObject(ServletInputStream inputStream) {
        try {
            return objectMapper.readTree(inputStream);
        } catch (Exception e) {
            log.error("Failed to parse input stream to JsonNode", e);
            throw new BusinessException(CommonCode.ERROR);
        }
    }
}
