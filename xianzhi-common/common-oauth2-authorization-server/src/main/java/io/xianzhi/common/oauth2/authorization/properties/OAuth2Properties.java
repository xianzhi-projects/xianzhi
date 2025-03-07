package io.xianzhi.common.oauth2.authorization.properties;

import lombok.Data;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

/**
 * OAuth2配置
 *
 * @author Max
 * @since 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "xianzhi.security.oauth2")
public class OAuth2Properties implements InitializingBean {

    /**
     * 发布机构
     */
    private String issuer = "https://www.xianzhi.io";

    /**
     * rsa文件路径
     */
    private String keyPairPath;

    /**
     * ras密码
     */
    private String keyPairPassword;

    /**
     * ras别名
     */
    private String keyPairAlias;


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
        Assert.hasText(keyPairPath, "RSA file path is empty");
        Assert.hasText(keyPairPassword, "RSA file password is empty");
        Assert.hasText(keyPairAlias, "RSA file alias is empty");
    }
}
