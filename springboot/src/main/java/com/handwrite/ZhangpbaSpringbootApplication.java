package com.handwrite;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(WebServerAutoConfiguration.class)// 需要扫描到这个类，让spring管理
@ComponentScan
public @interface ZhangpbaSpringbootApplication {

}
