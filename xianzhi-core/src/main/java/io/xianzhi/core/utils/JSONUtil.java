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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.exception.BusinessException;
import jakarta.servlet.ServletInputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Utility class for working with JSON data.
 * <p>
 * This class provides methods for converting objects to JSON strings, parsing JSON strings into objects,
 * and converting objects to other types. It uses Jackson's {@link ObjectMapper} to perform JSON operations.
 * </p>
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
public class JSONUtil {

    /**
     * ObjectMapper used for JSON serialization and deserialization.
     */
    public static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Register module for Java 8 Time API
    }

    /**
     * Private constructor to prevent instantiation.
     *
     * @since 1.0.0
     */
    private JSONUtil() {
    }

    /**
     * Converts an object to a JSON string.
     *
     * @param object The object to be converted.
     * @return The corresponding JSON string, or {@code null} if the object is {@code null}.
     * @since 1.0.0
     */
    public static String toJSONString(Object object) {
        if (null == object) {
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
     * Parses a JSON string into an object of the specified type.
     *
     * @param json  The JSON string to be parsed.
     * @param clazz The class type to map the JSON string to.
     * @param <R>   The type of the object.
     * @return The parsed object, or {@code null} if the JSON string is empty.
     * @since 1.0.0
     */
    public static <R> R parseObject(String json, Class<R> clazz) {
        if (!StringUtils.hasText(json)) {
            return null;
        }
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error("Failed to convert JSON string to object", e);
            throw new BusinessException(CommonCode.ERROR);
        }
    }

    /**
     * Parses a JSON string into a list of objects of the specified type.
     *
     * @param json  The JSON string to be parsed.
     * @param clazz The class type to map the JSON string to.
     * @param <R>   The type of the objects.
     * @return The parsed list of objects, or {@code null} if the JSON string is empty.
     * @since 1.0.0
     */
    public static <R> List<R> parseArray(String json, Class<R> clazz) {
        if (!StringUtils.hasText(json)) {
            return null;
        }
        try {
            return objectMapper.readValue(json,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            log.error("Failed to convert JSON string to object list", e);
            throw new BusinessException(CommonCode.ERROR);
        }
    }

    /**
     * Converts an object to another object of the specified type.
     *
     * @param obj   The object to be converted.
     * @param clazz The target class type to convert the object to.
     * @param <R>   The type of the resulting object.
     * @return The converted object, or {@code null} if the original object is {@code null}.
     * @since 1.0.0
     */
    public static <R> R toObject(Object obj, Class<R> clazz) {
        if (null == obj) {
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
     * Converts an object to a list of objects of the specified type.
     *
     * @param obj   The object to be converted.
     * @param clazz The target class type to convert the object to.
     * @param <R>   The type of the resulting object list.
     * @return The converted list of objects, or {@code null} if the original object is empty.
     * @since 1.0.0
     */
    public static <R> List<R> toObjectList(Object obj, Class<R> clazz) {
        if (ObjectUtils.isEmpty(obj)) {
            return null;
        }
        try {
            return objectMapper.convertValue(obj,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            log.error("Failed to convert object to object list", e);
            throw new BusinessException(CommonCode.ERROR);
        }
    }

    /**
     * Parses a {@link ServletInputStream} into a {@link JsonNode}.
     *
     * @param inputStream The input stream containing the JSON data.
     * @return The parsed {@link JsonNode}.
     * @since 1.0.0
     */
    public static JsonNode parseObject(ServletInputStream inputStream) {
        try {
            return objectMapper.readTree(inputStream);
        } catch (Exception e) {
            log.error("Failed to parse JSON from input stream", e);
            throw new BusinessException(CommonCode.ERROR);
        }
    }
}
