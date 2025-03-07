package io.xianzhi.common.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 安全配置类
 *
 * @author Max
 * @since 1.0.0
 */
public class SecurityConfig {


    /**
     * 密码加密
     *
     * @return 密码加密
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
