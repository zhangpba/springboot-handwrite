package com.handwrite;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangpba
 * @description 自动配置
 * @date 2023/2/17
 */

@Configuration
public class WebServerAutoConfiguration {

    // @Conditional 是否符合条件
    @Bean
    @Conditional(TomcatCondition.class) // 项目中是否有tomcat依赖
    public TomcatWebServer tomcatWebServer(){
        return new TomcatWebServer();
    }

    @Bean
    @Conditional(JettyCondition.class)
    public JettyWebServer jettyWebServer(){
        return new JettyWebServer();
    }
}
