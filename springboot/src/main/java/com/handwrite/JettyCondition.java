package com.handwrite;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author zhangpba
 * @description 是否实现jetty的条件类
 * @date 2023/2/17
 */
public class JettyCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 判断项目中是否有 jetty依赖
        try {
            context.getClassLoader().loadClass("org.eclipse.jetty.server.Server");
            // 有的话返回true
            return true;
        } catch (ClassNotFoundException e) {
            // 类没有加载到 返回false
            return false;
        }
    }
}
