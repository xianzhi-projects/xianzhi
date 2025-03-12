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
     * Redis 缓存操作模板，用于存储和检查幂等性标记。
     */
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 幂等性配置属性，包含是否启用、存储前缀、过期时间等。
     */
    private final IdempotentProperties idempotentProperties;

    /**
     * 处理接口幂等性逻辑。
     * 在目标方法执行前检查幂等性标记，若重复请求则抛出异常；执行后存储标记。
     *
     * @param joinPoint  切点对象，包含目标方法的上下文信息，用于执行原始方法。
     * @param idempotent 幂等性注解，标注在目标方法上，定义了幂等性相关的配置（如过期时间）。
     * @return 返回目标方法的执行结果。
     * @throws Throwable 如果目标方法执行失败或幂等性校验失败，抛出异常。
     */
    @Around("@annotation(idempotent)")
    public Object handleIdempotency(ProceedingJoinPoint joinPoint, Idempotent idempotent) throws Throwable {
        // 检查是否启用幂等性校验
        if (!idempotentProperties.isEnable()) {
            log.debug("幂等性校验未启用，直接执行目标方法");
            return joinPoint.proceed();
        }

        // 获取 HTTP 请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            log.error("无法获取请求上下文，可能不在 Web 环境中");
            throw new BusinessException("500", "无法获取请求上下文");
        }
        HttpServletRequest request = attributes.getRequest();

        // 从请求头中获取幂等性 Key
        String idempotencyKey = request.getHeader(idempotentProperties.getRequestHeader());
        if (!StringUtils.hasText(idempotencyKey)) {
            log.error("请求头中未传递幂等性 Key，header: {}", idempotentProperties.getRequestHeader());
            throw new BusinessException("500", "幂等性 Key 未提供");
        }

        // 构造 Redis Key
        String redisKey = String.format(idempotentProperties.getStorePrefix(), idempotencyKey);
        log.debug("构造 Redis Key: {}", redisKey);

        // 使用 Redis 的 setIfAbsent 实现原子性检查和设置
        Boolean setIfAbsent = redisTemplate.opsForValue().setIfAbsent(redisKey, "PROCESSING",
                idempotentProperties.getStoreExpire(), TimeUnit.SECONDS);
        if (Boolean.FALSE.equals(setIfAbsent)) {
            log.warn("检测到重复请求，幂等 Key: {}", idempotencyKey);
            throw new BusinessException("500", "重复请求");
        }

        try {
            // 执行目标方法
            log.debug("开始执行目标方法，幂等 Key: {}", idempotencyKey);
            Object result = joinPoint.proceed();
            log.debug("目标方法执行成功，幂等 Key: {}", idempotencyKey);
            // 更新缓存为成功状态（可选：存储实际结果）
            redisTemplate.opsForValue().set(redisKey, "SUCCESS",
                    idempotentProperties.getStoreExpire(), TimeUnit.SECONDS);
            return result;
        } catch (Throwable ex) {
            // 如果业务方法执行失败，删除 Redis Key 以允许重试
            redisTemplate.delete(redisKey);
            log.error("目标方法执行失败，幂等 Key: {}, 错误信息: {}", idempotencyKey, ex.getMessage(), ex);
            throw ex;
        }
    }

}