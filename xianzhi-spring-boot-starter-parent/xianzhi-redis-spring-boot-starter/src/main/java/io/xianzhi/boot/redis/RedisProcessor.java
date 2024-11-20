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

import io.xianzhi.core.utils.JSONUtil;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Redis Processor class for handling Redis operations such as setting, getting, deleting data,
 * managing expiration, and hash field operations.
 *
 * @author Max
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class RedisProcessor {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * Save List-type data. <br>
     *
     * @param key   The key to store the value.
     * @param value The value to be stored.
     * @since 1.0.0
     */
    public void vSet(String key, Object value) {
        vSet(key, value, -1L);
    }

    /**
     * Save List-type data with expiration time. <br>
     *
     * @param key    The key to store the value.
     * @param value  The value to be stored.
     * @param expire The expiration time in seconds.
     * @since 1.0.0
     */
    public void vSet(String key, Object value, long expire) {
        vSet(key, value, expire, TimeUnit.SECONDS);
    }

    /**
     * Save List-type data with expiration time and time unit. <br>
     *
     * @param key      The key to store the value.
     * @param value    The value to be stored.
     * @param expire   The expiration time.
     * @param timeUnit The time unit for expiration.
     * @since 1.0.0
     */
    public void vSet(String key, Object value, long expire, TimeUnit timeUnit) {
        if (StringUtils.hasText(key) && value != null) {
            if (expire > 0) {
                redisTemplate.opsForValue().set(key, value, expire, timeUnit);
            } else {
                redisTemplate.opsForValue().set(key, value);
            }
        }
    }

    /**
     * Retrieve data by key. <br>
     *
     * @param key The key of the value to retrieve.
     * @return The stored value.
     * @since 1.0.0
     */
    public Object vGet(String key) {
        if (StringUtils.hasText(key)) {
            return redisTemplate.opsForValue().get(key);
        }
        return null;
    }

    /**
     * Retrieve data and automatically convert to specified type. <br>
     *
     * @param key    The key of the value to retrieve.
     * @param tClass The type to which the value should be converted.
     * @param <T>    The type parameter.
     * @return The value converted to the specified type.
     * @since 1.0.0
     */
    public <T> T vGet(String key, Class<T> tClass) {
        if (StringUtils.hasText(key)) {
            Object data = redisTemplate.opsForValue().get(key);
            if (null == data || null == tClass) {
                return null;
            }
            return JSONUtil.toObject(data, tClass);
        }
        return null;
    }

    /**
     * Retrieve List-type data and automatically convert to specified type. <br>
     *
     * @param key    The key of the value to retrieve.
     * @param tClass The type to which the value should be converted.
     * @param <T>    The type parameter.
     * @return The value converted to a list of the specified type.
     * @since 1.0.0
     */
    public <T> List<T> vGetList(String key, Class<T> tClass) {
        if (StringUtils.hasText(key)) {
            Object data = redisTemplate.opsForValue().get(key);
            if (null == data) {
                return null;
            }
            return JSONUtil.toObjectList(data, tClass);
        }
        return null;
    }

    /**
     * Delete data by key(s). <br>
     *
     * @param keys The keys to delete.
     * @since 1.0.0
     */
    public void delete(String... keys) {
        delete(List.of(keys));
    }

    /**
     * Delete data by key(s). <br>
     *
     * @param keys The keys to delete.
     * @since 1.0.0
     */
    public void delete(List<String> keys) {
        if (ObjectUtils.isEmpty(keys)) {
            return;
        }
        redisTemplate.delete(keys);
    }

    /**
     * Delete hash field by key. <br>
     *
     * @param key  The large key.
     * @param item The small key (field).
     * @since 1.0.0
     */
    public void hDeleted(String key, String item) {
        if (StringUtils.hasText(key) && StringUtils.hasText(item)) {
            redisTemplate.opsForHash().delete(key, item);
        }
    }

    /**
     * Set expiration time for a key. <br>
     *
     * @param key    The key to set expiration for.
     * @param expire The expiration time in seconds.
     * @since 1.0.0
     */
    public void expire(String key, long expire) {
        expire(key, expire, TimeUnit.SECONDS);
    }

    /**
     * Set expiration time for a key. <br>
     *
     * @param key      The key to set expiration for.
     * @param expire   The expiration time.
     * @param timeUnit The time unit for expiration.
     * @since 1.0.0
     */
    public void expire(String key, long expire, TimeUnit timeUnit) {
        if (StringUtils.hasText(key) && expire > 0) {
            redisTemplate.expire(key, expire, timeUnit);
        }
    }

    /**
     * Get the remaining expiration time for a key in seconds. <br>
     *
     * @param key The key to check expiration for.
     * @return The expiration time in seconds.
     * @since 1.0.0
     */
    public Long getExpire(String key) {
        if (StringUtils.hasText(key)) {
            return redisTemplate.getExpire(key);
        }
        return null;
    }

    /**
     * Get the remaining expiration time for a key with specified time unit. <br>
     *
     * @param key      The key to check expiration for.
     * @param timeUnit The time unit for the expiration time.
     * @return The expiration time.
     * @since 1.0.0
     */
    public Long getExpire(String key, TimeUnit timeUnit) {
        if (StringUtils.hasText(key)) {
            return redisTemplate.getExpire(key, timeUnit);
        }
        return null;
    }

    /**
     * Check if a key exists. <br>
     *
     * @param key The key to check.
     * @return True if the key exists, otherwise false.
     * @since 1.0.0
     */
    public Boolean hasKey(String key) {
        if (StringUtils.hasText(key)) {
            return redisTemplate.hasKey(key);
        }
        return false;
    }

    /**
     * Set hash field value. <br>
     *
     * @param key   The key to store the hash in.
     * @param field The field in the hash.
     * @param value The value to set for the field.
     * @since 1.0.0
     */
    public void hSet(String key, String field, Object value) {
        if (StringUtils.hasText(key) && StringUtils.hasText(field) && value != null) {
            redisTemplate.opsForHash().put(key, field, value);
        }
    }

    /**
     * Get hash field value. <br>
     *
     * @param key   The key to retrieve the hash from.
     * @param field The field to retrieve.
     * @return The value of the hash field.
     * @since 1.0.0
     */
    public Object hGet(String key, String field) {
        if (StringUtils.hasText(key) && StringUtils.hasText(field)) {
            return redisTemplate.opsForHash().get(key, field);
        }
        return null;
    }

    /**
     * Get hash field value and automatically convert to specified type. <br>
     *
     * @param key    The key to retrieve the hash from.
     * @param field  The field to retrieve.
     * @param tClass The type to which the value should be converted.
     * @param <T>    The type parameter.
     * @return The value of the hash field converted to the specified type.
     * @since 1.0.0
     */
    public <T> T hGet(String key, String field, Class<T> tClass) {
        if (StringUtils.hasText(key) && StringUtils.hasText(field)) {
            Object data = redisTemplate.opsForHash().get(key, field);
            return data == null ? null : JSONUtil.toObject(data, tClass);
        }
        return null;
    }

    /**
     * Get hash field value as a list and automatically convert to specified type. <br>
     *
     * @param key    The key to retrieve the hash from.
     * @param field  The field to retrieve.
     * @param tClass The type to which the value should be converted.
     * @param <T>    The type parameter.
     * @return The value of the hash field as a list of the specified type.
     * @since 1.0.0
     */
    public <T> List<T> hGetList(String key, String field, Class<T> tClass) {
        if (StringUtils.hasText(key) && StringUtils.hasText(field)) {
            Object data = redisTemplate.opsForHash().get(key, field);
            return data == null ? null : JSONUtil.toObjectList(data, tClass);
        }
        return null;
    }

    /**
     * Delete hash field(s). <br>
     *
     * @param key    The key where the hash exists.
     * @param fields The fields to delete from the hash.
     * @since 1.0.0
     */
    public void hDelete(String key, Object... fields) {
        if (StringUtils.hasText(key) && fields != null && fields.length > 0) {
            redisTemplate.opsForHash().delete(key, fields);
        }
    }

    /**
     * Check if hash field exists. <br>
     *
     * @param key   The key to check the hash from.
     * @param field The field to check in the hash.
     * @return True if the field exists, otherwise false.
     * @since 1.0.0
     */
    public Boolean hHasKey(String key, String field) {
        if (StringUtils.hasText(key) && StringUtils.hasText(field)) {
            return redisTemplate.opsForHash().hasKey(key, field);
        }
        return false;
    }

}
