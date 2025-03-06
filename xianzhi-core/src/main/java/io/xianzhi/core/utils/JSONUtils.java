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
 * JSON工具类
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
public class JSONUtils {

    /**
     * ObjectMapper
     */
    public static final ObjectMapper objectMapper;
    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    static {
        objectMapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(module)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.getFactory().configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);

    }

    /**
     * 私有化构造器，防止被实例化
     */
    private JSONUtils() {
    }


    /**
     * 将对象转换为JSON字符串
     *
     * @param object 对象
     * @return JSON字符串
     */
    public static String toJSONString(Object object) {
        if (null == object) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("对象转JSON字符串异常", e);
            throw new BusinessException(CommonCode.ERROR);
        }
    }

    /**
     * 将JSON字符串转换为对象
     *
     * @param json  JSON字符串
     * @param clazz 对象类型
     * @param <R>   对象泛型
     * @return 对象
     */
    public static <R> R parseObject(String json, Class<R> clazz) {
        if (!StringUtils.hasText(json)) {
            return null;
        }
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error("JSON字符串转对象异常", e);
            throw new BusinessException(CommonCode.ERROR);
        }
    }

    /**
     * 将JSON字符串转换为对象列表
     *
     * @param json  JSON字符串
     * @param clazz 对象类型
     * @param <R>   对象泛型
     * @return 对象列表
     */
    public static <R> List<R> parseArray(String json, Class<R> clazz) {
        if (!StringUtils.hasText(json)) {
            return null;
        }
        try {
            return objectMapper.readValue(json,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            log.error("JSON字符串转对象异常", e);
            throw new BusinessException(CommonCode.ERROR);
        }
    }

    /**
     * 对象转对象
     *
     * @param obj   对象
     * @param clazz 类型
     * @param <R>   对象泛型
     * @return 对象
     */
    public static <R> R toObject(Object obj, Class<R> clazz) {
        if (null == obj) {
            return null;
        }
        try {
            return objectMapper.convertValue(obj, clazz);
        } catch (Exception e) {
            log.error("对象转对象异常", e);
            throw new BusinessException(CommonCode.ERROR);
        }
    }

    /**
     * 对象转对象列表
     *
     * @param obj   对象
     * @param clazz 类型
     * @param <R>   对象泛型
     * @return 对象列表
     */
    public static <R> List<R> toObjectList(Object obj, Class<R> clazz) {
        if (ObjectUtils.isEmpty(obj)) {
            return null;
        }
        try {
            return objectMapper.convertValue(obj,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            log.error("对象转对象异常", e);
            throw new BusinessException(CommonCode.ERROR);
        }
    }

    /**
     * 流转JsonNode对象
     *
     * @param inputStream 输入流
     * @return JsonNode对象
     */
    public static JsonNode parseObject(ServletInputStream inputStream) {
        try {
            return objectMapper.readTree(inputStream);
        } catch (Exception e) {
            log.error("JSON字符串转对象异常", e);
            throw new BusinessException(CommonCode.ERROR);
        }
    }
}
