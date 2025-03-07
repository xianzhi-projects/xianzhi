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

import io.xianzhi.core.result.ResponseResult;
import io.xianzhi.core.result.Result;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 响应相关工具类
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
public class ResponseUtils {

    /**
     * 私有化构造器，防止被实例化
     */
    private ResponseUtils() {
    }

    /**
     * 响应JSON到前端
     *
     * @param result   响应的数据
     * @param response 响应对象
     */
    public static void responseUtf8(Result result, HttpServletResponse response) {
        try {
            if (!(result instanceof ResponseResult<?>)) {
                result = new ResponseResult<>(result, null);
            }
            // 设置响应头和字符编码
            response.setContentType("application/json;charset=UTF-8");
            // 将JSON字符串写入响应体中
            response.getWriter().write(JSONUtils.toJSONString(result));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取响应对象
     *
     * @return 响应对象
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            return attributes.getResponse();
        }
        log.error("无法获取HttpServletResponse对象");
        return null;
    }
}
