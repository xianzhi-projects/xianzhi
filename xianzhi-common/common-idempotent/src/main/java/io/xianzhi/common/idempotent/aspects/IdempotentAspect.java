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
 * Idempotent Aspect
 * This class implements an Aspect-Oriented Programming (AOP) aspect to enforce idempotency for
 * methods annotated with @Idempotent. It intercepts method calls, checks for duplicate requests
 * using a Redis-based mechanism, and ensures that repeated invocations do not result in unintended
 * side effects. The aspect leverages Redis for atomic key storage and expiration, and it integrates
 * with Spring’s web context to extract idempotency keys from HTTP request headers. It is registered
 * as a Spring component and uses SLF4J for logging.
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
     * Redis Template
     * This field provides a RedisTemplate instance for interacting with Redis, used to store and
     * check idempotency markers. It enables atomic operations (e.g., setIfAbsent) to ensure thread-safe
     * handling of idempotency keys. The template is injected via constructor-based dependency injection.
     */
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * Idempotent Properties
     * This field holds configuration properties for idempotency enforcement, such as whether it is
     * enabled, the storage prefix for Redis keys, expiration time, and the request header name for
     * the idempotency key. It is injected via constructor-based dependency injection.
     */
    private final IdempotentProperties idempotentProperties;

    /**
     * Handle Idempotency Logic
     * This method defines the around advice for methods annotated with @Idempotent. It performs the
     * following steps:
     * 1. Checks if idempotency is enabled; if not, proceeds with the target method directly.
     * 2. Retrieves the HTTP request and extracts the idempotency key from the specified header.
     * 3. Constructs a Redis key and uses setIfAbsent to atomically check for duplicates.
     * 4. If a duplicate is detected, throws an exception; otherwise, executes the target method.
     * 5. Updates the Redis key on success or removes it on failure to allow retries.
     *
     * @param joinPoint  The ProceedingJoinPoint object providing access to the target method’s context
     *                   and allowing its execution.
     * @param idempotent The @Idempotent annotation instance from the target method, providing
     *                   configuration details (though currently unused beyond marking the method).
     * @return The result of the target method execution.
     * @throws Throwable If the target method fails or idempotency validation fails, the exception
     *                   is propagated to the caller.
     */
    @Around("@annotation(idempotent)")
    public Object handleIdempotency(ProceedingJoinPoint joinPoint, Idempotent idempotent) throws Throwable {
        // Check if idempotency validation is enabled
        if (!idempotentProperties.isEnable()) {
            log.debug("Idempotency validation is disabled; proceeding with target method execution");
            return joinPoint.proceed();
        }

        // Retrieve HTTP request object from the current web context
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            log.error("Unable to retrieve request context; possibly not in a web environment");
            throw new BusinessException(CommonCode.ERROR);
        }
        HttpServletRequest request = attributes.getRequest();

        // Extract idempotency key from the request header
        String idempotencyKey = request.getHeader(idempotentProperties.getRequestHeader());
        if (!StringUtils.hasText(idempotencyKey)) {
            log.error("Idempotency key not provided in request header: {}", idempotentProperties.getRequestHeader());
            throw new BusinessException(CommonCode.PARAM_CHECK_ERROR);
        }

        // Construct Redis key using the configured prefix and idempotency key
        String redisKey = String.format(idempotentProperties.getStorePrefix(), idempotencyKey);
        log.debug("Constructed Redis key: {}", redisKey);

        // Atomically check and set the idempotency marker in Redis
        Boolean setIfAbsent = redisTemplate.opsForValue().setIfAbsent(redisKey, "PROCESSING",
                idempotentProperties.getStoreExpire(), TimeUnit.SECONDS);
        if (Boolean.FALSE.equals(setIfAbsent)) {
            log.warn("Duplicate request detected for idempotency key: {}", idempotencyKey);
            throw new BusinessException(CommonCode.IDEMPOTENT_REQUEST);
        }

        try {
            // Execute the target method
            log.debug("Starting execution of target method with idempotency key: {}", idempotencyKey);
            Object result = joinPoint.proceed();
            log.debug("Target method executed successfully with idempotency key: {}", idempotencyKey);

            // Update Redis with success status (optional: could store result instead)
            redisTemplate.opsForValue().set(redisKey, "SUCCESS",
                    idempotentProperties.getStoreExpire(), TimeUnit.SECONDS);
            return result;
        } catch (Throwable ex) {
            // Remove Redis key on failure to allow retries
            redisTemplate.delete(redisKey);
            log.error("Target method execution failed for idempotency key: {}, error message: {}",
                    idempotencyKey, ex.getMessage(), ex);
            throw ex;
        }
    }
}