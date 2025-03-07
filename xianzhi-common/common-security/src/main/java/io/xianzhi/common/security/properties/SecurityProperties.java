package io.xianzhi.common.security.properties;

import lombok.Data;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 安全配置
 *
 * @author Max
 * @since 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "xianzhi.security")
public class SecurityProperties implements InitializingBean {

    /**
     * token过期时间，单位是小时
     */
    private Integer tokenExpire = 2;

    /**
     * 刷新token过期时间，单位是小时
     */
    private Integer refreshTokenExpire = tokenExpire * 2;

    /**
     * 放行地址，白名单 即不用登录即可访问的资源
     */
    private List<String> permitAllList = new ArrayList<>();


    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an
     *                   essential property) or if initialization fails for any other reason
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        if (tokenExpire <= 0) {
            throw new IllegalArgumentException("tokenExpire must be greater than 0");
        }
        if (refreshTokenExpire <= 0) {
            throw new IllegalArgumentException("refreshTokenExpire must be greater than 0");
        }
        if (refreshTokenExpire < tokenExpire) {
            throw new IllegalArgumentException("refreshTokenExpire must be greater than tokenExpire");
        }
        permitAllList.add("/favicon.ico");
    }
}

