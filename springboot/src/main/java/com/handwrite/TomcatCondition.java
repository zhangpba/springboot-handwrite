package com.handwrite;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author zhangpba
 * @description 是否实现tomcat的条件类
 * @date 2023/2/17
 */
public class TomcatCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        // 判断项目中是否有 tomcat依赖
        try {
            context.getClassLoader().loadClass("org.apache.catalina.startup.Tomcat");
            // 有的话返回true
            return true;
        } catch (ClassNotFoundException e) {
            // 类没有加载到 返回false
            return false;
        }
    }
}
