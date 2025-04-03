
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

package io.xianzhi.common.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.xianzhi.core.utils.JSONUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * RedisTemplate配置
 *
 * @author Max
 * @since 1.0.0
 */
public class RedisTemplateConfig {




    /**
     * 配置 RedisTemplate，这里使用了 StringRedisSerializer 序列化键，
     * 使用 GenericJackson2JsonRedisSerializer 序列化值。
     *
     * @param redisConnectionFactory             Redis连接工厂，负责管理与 Redis 的连接。
     * @param genericJackson2JsonRedisSerializer 用于序列化值的 JSON 序列化器。
     * @param stringRedisSerializer              用于序列化键的字符串序列化器。
     * @return 配置完成的 RedisTemplate。
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory,
                                                       GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer,
                                                       StringRedisSerializer stringRedisSerializer) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();

        // 设置 Redis 键的序列化方式，使用 StringRedisSerializer
        template.setKeySerializer(stringRedisSerializer);

        // 设置 Redis 值的序列化方式，使用 GenericJackson2JsonRedisSerializer
        template.setValueSerializer(genericJackson2JsonRedisSerializer);

        // 设置 Redis 哈希键的序列化方式，使用 StringRedisSerializer
        template.setHashKeySerializer(stringRedisSerializer);

        // 设置 Redis 哈希值的序列化方式，使用 GenericJackson2JsonRedisSerializer
        template.setHashValueSerializer(genericJackson2JsonRedisSerializer);

        // 设置 Redis 的连接工厂
        template.setConnectionFactory(redisConnectionFactory);

        return template;
    }

    /**
     * 配置 StringRedisSerializer，主要用于 Redis 的键序列化。
     * StringRedisSerializer 会将所有键序列化为字符串，支持层级结构的键名（例如包含冒号的键名）。
     *
     * @return 用于序列化键的 StringRedisSerializer。
     */
    @Bean
    public StringRedisSerializer stringRedisSerializer() {
        return new StringRedisSerializer();
    }

    /**
     * 配置 GenericJackson2JsonRedisSerializer，用于 Redis 值的序列化。
     * 使用 Jackson 序列化对象为 JSON 格式，并配置 ObjectMapper 的相关序列化和反序列化设置。
     *
     * @return 用于序列化 Redis 值的 GenericJackson2JsonRedisSerializer。
     * META-INF
     */
    @Bean
    public GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer() {
        // 创建一个新的 ObjectMapper 用于配置 JSON 序列化行为
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(LocalDate.class, new LocalDateSerializer(JSONUtils.dateFormatter));
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(JSONUtils.dateTimeFormatter));

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(module)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.getFactory().configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);

        // 设置可见性：允许序列化所有字段，包括 private、protected、public 字段
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        // 创建一个宽松的 PolymorphicTypeValidator，允许 OAuth2ClientDO
        BasicPolymorphicTypeValidator validator = BasicPolymorphicTypeValidator.builder()
                .allowIfBaseType(Object.class) // 允许 Object 及其子类
                .build();
        // 激活多态类型处理，使用 WRAPPER_ARRAY 将类型信息包装为数组
        objectMapper.activateDefaultTyping(
                validator,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );

        // 设置反序列化时忽略未知属性，避免在 JSON 数据不完整时抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.deactivateDefaultTyping();
        // 配置序列化时，遇到空对象不抛出异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // 配置反序列化时，当基本类型字段为 null 时抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);

        // 返回配置好的 GenericJackson2JsonRedisSerializer，使用自定义的 ObjectMapper
        return new GenericJackson2JsonRedisSerializer(objectMapper);
    }
}
