
package io.xianzhi.common.redis;

import io.xianzhi.core.utils.JSONUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Max
 * @since 1.0.0
 */
@Slf4j
public class RedisHandler {


    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 保存List类型数据<br>
     *
     * @param key   键
     * @param value 值
     */
    public void vSet(String key, Object value) {
        vSet(key, value, -1L);
    }

    /**
     * 保存List类型数据<br>
     *
     * @param key    键
     * @param value  值
     * @param expire 过期时间 单位秒
     */
    public void vSet(String key, Object value, long expire) {
        vSet(key, value, expire, TimeUnit.SECONDS);
    }

    /**
     * 保存List类型数据<br>
     *
     * @param key      键
     * @param value    值
     * @param expire   过期时间
     * @param timeUnit 时间单位
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
     * 获取数据<br>
     *
     * @param key 键
     * @return 数据
     */
    public Object vGet(String key) {
        if (StringUtils.hasText(key)) {
            return redisTemplate.opsForValue().get(key);
        }
        return null;
    }

    /**
     * 获取数据,并自动转换类型<br>
     *
     * @param key    键
     * @param tClass 类型
     * @param <T>    类型
     * @return 数据
     */
    public <T> T vGet(String key, Class<T> tClass) {
        if (StringUtils.hasText(key)) {
            Object data = redisTemplate.opsForValue().get(key);
            if (null == data || null == tClass) {
                return null;
            }
            return JSONUtils.toObject(data, tClass);
        }
        return null;
    }

    /**
     * 获取List类型数据，并自动转换类型<br>
     *
     * @param key    键
     * @param tClass 类型
     * @param <T>    类型
     * @return 数据
     */
    public <T> List<T> vGetList(String key, Class<T> tClass) {
        if (StringUtils.hasText(key)) {
            Object data = redisTemplate.opsForValue().get(key);
            if (null == data) {
                return null;
            }
            return JSONUtils.toObjectList(data, tClass);
        }
        return null;
    }

    /**
     * 删除数据<br>
     *
     * @param keys 键
     */
    public void delete(String... keys) {
        delete(List.of(keys));
    }

    /**
     * 删除数据<br>
     *
     * @param keys 键
     */
    public void delete(List<String> keys) {
        if (ObjectUtils.isEmpty(keys)) {
            return;
        }
        redisTemplate.delete(keys);
    }

    /**
     * 删除数据<br>
     *
     * @param key  大key
     * @param item 小key
     */
    public void hDeleted(String key, String item) {
        if (StringUtils.hasText(key) && StringUtils.hasText(item)) {
            redisTemplate.opsForHash().delete(key, item);
        }
    }

    /**
     * 设置过期时间<br>
     *
     * @param key    键
     * @param expire 过期时间 单位秒
     */
    public void expire(String key, long expire) {
        expire(key, expire, TimeUnit.SECONDS);
    }

    /**
     * 设置过期时间<br>
     *
     * @param key      键
     * @param expire   过期时间
     * @param timeUnit 时间单位
     */
    public void expire(String key, long expire, TimeUnit timeUnit) {
        if (StringUtils.hasText(key) && expire > 0) {
            redisTemplate.expire(key, expire, timeUnit);
        }
    }

    /**
     * 获取过期时间<br>
     *
     * @param key 键
     * @return 过期时间，单位秒
     */
    public Long getExpire(String key) {
        if (StringUtils.hasText(key)) {
            return redisTemplate.getExpire(key);
        }
        return null;
    }

    /**
     * 获取过期时间<br>
     *
     * @param key      键
     * @param timeUnit 时间单位
     * @return 过期时间
     */
    public Long getExpire(String key, TimeUnit timeUnit) {
        if (StringUtils.hasText(key)) {
            return redisTemplate.getExpire(key, timeUnit);
        }
        return null;
    }

    /**
     * 判断key是否存在<br>
     *
     * @param key 键
     * @return 是否存在
     */
    public Boolean hasKey(String key) {
        if (StringUtils.hasText(key)) {
            return redisTemplate.hasKey(key);
        }
        return false;
    }

    /**
     * 设置hash数据<br>
     *
     * @param key   键
     * @param field 字段
     * @param value 值
     */
    public void hSet(String key, String field, Object value) {
        if (StringUtils.hasText(key) && StringUtils.hasText(field) && value != null) {
            redisTemplate.opsForHash().put(key, field, value);
        }
    }

    /**
     * 获取hash数据<br>
     *
     * @param key   键
     * @param field 字段
     * @return 数据
     */
    public Object hGet(String key, String field) {
        if (StringUtils.hasText(key) && StringUtils.hasText(field)) {
            return redisTemplate.opsForHash().get(key, field);
        }
        return null;
    }

    /**
     * 获取hash数据,并自动转换类型<br>
     *
     * @param key    键
     * @param items  字段
     * @param tClass 类型
     * @param <T>    类型
     * @return 数据
     */
    public <T> List<T> hGet(String key, Collection<Object> items, Class<T> tClass) {
        if (StringUtils.hasText(key) && !ObjectUtils.isEmpty(items)) {
            List<Object> data = redisTemplate.opsForHash().multiGet(key, items);
            return JSONUtils.toObjectList(data, tClass);
        }
        return null;
    }

    /**
     * 获取hash数据,并自动转换类型<br>
     *
     * @param key    键
     * @param field  字段
     * @param tClass 类型
     * @param <T>    类型
     * @return 数据
     */
    public <T> T hGet(String key, String field, Class<T> tClass) {
        if (StringUtils.hasText(key) && StringUtils.hasText(field)) {
            Object data = redisTemplate.opsForHash().get(key, field);
            return data == null ? null : JSONUtils.toObject(data, tClass);
        }
        return null;
    }


    public <T> List<T> hGetList(String key, String field, Class<T> tClass) {
        if (StringUtils.hasText(key) && StringUtils.hasText(field)) {
            Object data = redisTemplate.opsForHash().get(key, field);
            return data == null ? null : JSONUtils.toObjectList(data, tClass);
        }
        return null;
    }

    /**
     * 删除hash数据<br>
     *
     * @param key    键
     * @param fields 字段
     */
    public void hDelete(String key, Object... fields) {
        if (StringUtils.hasText(key) && fields != null && fields.length > 0) {
            redisTemplate.opsForHash().delete(key, fields);
        }
    }

    /**
     * 判断hash数据是否存在<br>
     *
     * @param key   键
     * @param field 字段
     * @return 是否存在
     */
    public Boolean hHasKey(String key, String field) {
        if (StringUtils.hasText(key) && StringUtils.hasText(field)) {
            return redisTemplate.opsForHash().hasKey(key, field);
        }
        return false;
    }

    /**
     * 设置set数据<br>
     *
     * @param key    键
     * @param values 值
     */
    public void sSet(String key, Object... values) {
        sSet(key, -1, values);
    }

    /**
     * 设置set数据<br>
     *
     * @param key    键
     * @param expire 过期时间 单位秒
     * @param values 值
     */
    public void sSet(String key, long expire, Object... values) {
        sSet(key, expire, TimeUnit.SECONDS, values);
    }

    /**
     * 设置set数据<br>
     *
     * @param key      键
     * @param expire   过期时间
     * @param timeUnit 时间单位
     * @param values   值
     */
    public void sSet(String key, long expire, TimeUnit timeUnit, Object... values) {
        if (StringUtils.hasText(key) && values != null && values.length > 0) {
            redisTemplate.opsForSet().add(key, values);
            if (expire > 0) {
                redisTemplate.expire(key, expire, timeUnit);
            }
        }

    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 步长
     * @return 递增后的值
     */
    public Long increment(String key, long delta) {
        if (StringUtils.hasText(key)) {
            return redisTemplate.opsForValue().increment(key, delta);
        }
        return null;
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 步长
     * @return 递减后的值
     */
    public Long decrement(String key, long delta) {
        if (StringUtils.hasText(key)) {
            return redisTemplate.opsForValue().decrement(key, delta);
        }
        return null;
    }
}
