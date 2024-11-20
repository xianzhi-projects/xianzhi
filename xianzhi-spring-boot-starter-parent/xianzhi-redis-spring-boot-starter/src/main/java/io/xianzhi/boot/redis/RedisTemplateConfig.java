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

package io.xianzhi.boot.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Configuration class for setting up RedisTemplate with custom serializers.
 * It uses StringRedisSerializer for serializing keys and GenericJackson2JsonRedisSerializer for serializing values.
 *
 * @author Max
 * @since 1.0.0
 */
public class RedisTemplateConfig {

    /**
     * Configures a RedisTemplate, using StringRedisSerializer for serializing keys
     * and GenericJackson2JsonRedisSerializer for serializing values.
     *
     * @param redisConnectionFactory             The Redis connection factory that manages the connection to Redis.
     * @param genericJackson2JsonRedisSerializer The JSON serializer used for serializing values.
     * @param stringRedisSerializer              The string serializer used for serializing keys.
     * @return A fully configured RedisTemplate.
     * @since 1.0.0
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory,
                                                       GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer,
                                                       StringRedisSerializer stringRedisSerializer) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();

        // Set the key serializer to StringRedisSerializer
        template.setKeySerializer(stringRedisSerializer);

        // Set the value serializer to GenericJackson2JsonRedisSerializer
        template.setValueSerializer(genericJackson2JsonRedisSerializer);

        // Set the hash key serializer to StringRedisSerializer
        template.setHashKeySerializer(stringRedisSerializer);

        // Set the hash value serializer to GenericJackson2JsonRedisSerializer
        template.setHashValueSerializer(genericJackson2JsonRedisSerializer);

        // Set the Redis connection factory
        template.setConnectionFactory(redisConnectionFactory);

        return template;
    }

    /**
     * Configures a StringRedisSerializer for serializing Redis keys.
     * StringRedisSerializer serializes all keys as strings, supporting hierarchical key names (e.g., keys with colons).
     *
     * @return A StringRedisSerializer for serializing keys.
     * @since 1.0.0
     */
    @Bean
    public StringRedisSerializer stringRedisSerializer() {
        return new StringRedisSerializer();
    }

    /**
     * Configures a GenericJackson2JsonRedisSerializer for serializing Redis values.
     * Uses Jackson to serialize objects to JSON format, with custom ObjectMapper settings for serialization and deserialization.
     *
     * @return A GenericJackson2JsonRedisSerializer for serializing Redis values.
     * @since 1.0.0
     */
    @Bean
    public GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer() {
        // Create a new ObjectMapper to configure JSON serialization behaviors
        ObjectMapper objectMapper = new ObjectMapper();

        // Set visibility to allow serialization of all fields (private, protected, public)
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        // Create a lenient polymorphic type validator, allowing for classes like OAuth2ClientDO
        BasicPolymorphicTypeValidator validator = BasicPolymorphicTypeValidator.builder()
                .allowIfBaseType(Object.class) // Allow Object and its subclasses
                .build();
        // Enable polymorphic type handling, wrapping type info as an array
        objectMapper.activateDefaultTyping(
                validator,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );

        // Configure ObjectMapper to ignore unknown properties during deserialization
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.deactivateDefaultTyping();

        // Configure ObjectMapper to not throw exceptions when serializing empty beans
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // Configure ObjectMapper to throw an exception when null is encountered for primitive types during deserialization
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);

        // Return the fully configured GenericJackson2JsonRedisSerializer with the custom ObjectMapper
        return new GenericJackson2JsonRedisSerializer(objectMapper);
    }
}
