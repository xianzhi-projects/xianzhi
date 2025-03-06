package io.xianzhi.system.bootstrap;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 服务启动类
 *
 * @author Max
 * @since 1.0.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"io.xianzhi.*.bootstrap"})
public class XianZhiApplication {

    public static void main(String[] args) {

    }
}
