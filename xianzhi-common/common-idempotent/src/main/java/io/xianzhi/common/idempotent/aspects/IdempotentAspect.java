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

package io.xianzhi.common.idempotent.aspects;


import io.xianzhi.common.idempotent.annotations.Idempotent;
import io.xianzhi.common.idempotent.properties.IdempotentProperties;
import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import java.util.concurrent.TimeUnit;

/**
 * 幂等切面
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class IdempotentAspect {

    /**
     * 缓存处理
     */
    private final RedisTemplate<String, Object> redisTemplate;
    /**
     * 幂等性配置
     */
    private final IdempotentProperties idempotentProperties;

    /**
     * 处理接口幂等性
     *
     * @param joinPoint  切点
     * @param idempotent 幂等性注解
     * @return Object
     * @throws Throwable Throwable
     */
    @Around("@annotation(idempotent)")
    public Object handleIdempotency(ProceedingJoinPoint joinPoint, Idempotent idempotent) throws Throwable {
        // 是否开启幂等性校验
        if (idempotentProperties.isEnable()) {
            // 从请求中获取幂等 Key
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String idempotencyKey = request.getHeader(idempotentProperties.getRequestHeader());
            if (!StringUtils.hasText(idempotencyKey)) {
                log.error("请求头中未传递幂等信息");
                throw new BusinessException(CommonCode.PARAM_CHECK_ERROR);
            }
            // 构造 Redis Key
            String redisKey = String.format(idempotentProperties.getStorePrefix(), idempotencyKey);
            // 尝试使用 Redis 的原子操作检查和保存
            if (Boolean.TRUE.equals(redisTemplate.hasKey(redisKey))) {
                // 如果 Key 已存在，则直接报错
                log.error("重复请求,幂等 Key:{}", idempotencyKey);
                throw new BusinessException(CommonCode.DUPLICATE_REQUEST);
            }

            try {
                // 执行目标方法
                Object result = joinPoint.proceed();
                // 缓存实际结果并覆盖标记
                redisTemplate.opsForValue().set(redisKey, new Object(), idempotentProperties.getStoreExpire(), TimeUnit.SECONDS);
                return result;
            } catch (Throwable ex) {
                // 如果业务方法执行异常，删除 Redis Key 以允许后续重试
                redisTemplate.delete(redisKey);
                log.error(ex.getMessage(), ex);
                throw ex;
            }
        }
        return joinPoint.proceed();
    }

}