package com.handwrite;

import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Map;

/**
 * @author zhangpba
 * @description 手写springboot
 *
 * https://www.bilibili.com/video/BV1U14y1T75B?p=4&spm_id_from=pageDriver&vd_source=230e3856f7a369d1a37ed417a64292b7
 * @date 2023/2/13
 */
public class ZhangpbaSpringApplication {

    public static void run(Class clazz) {

        // 创建spring容器
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(clazz);
        applicationContext.refresh();

        // 获取WebServer
        WebServer webServer = getWebServer(applicationContext);

        // 启动tomcat或者jetty
        webServer.start();

//        startTomcat(applicationContext);
    }

    /**
     * 获取WebServer
     * @param webApplicationContext
     * @return
     */
    private static WebServer getWebServer(WebApplicationContext webApplicationContext) {

        // key为beanName,value为Bean对象
        Map<String, WebServer> webServers = webApplicationContext.getBeansOfType(WebServer.class);

        // 有没有tomcat的依赖，有没有jetty的依赖
        if (webServers.isEmpty()) {
            throw new NullPointerException();
        }
        // 有两个 报错
        if (webServers.size() > 1) {
            throw new IllegalStateException();
        }

        // 返回唯一的一个
        return webServers.values().stream().findFirst().get();
    }


    /**
     * 启动tomcat
     *
     * @param webApplicationContext
     */
    private static void startTomcat(WebApplicationContext webApplicationContext) {

        Tomcat tomcat = new Tomcat();
        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");

        Connector connector = new Connector();
        connector.setPort(8081);

        Engine engine = new StandardEngine();
        engine.setDefaultHost("localhost");

        Host host = new StandardHost();
        host.setName("localhost");

        String contextPath = "";
        Context context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());

        host.addChild(context);
        engine.addChild(host);

        service.setContainer(engine);
        service.addConnector(connector);

        // 页面发送 http://127.0.0.1:8081/test 请求，在dispatchServlet处理
        tomcat.addServlet(contextPath, "dispatcher", new DispatcherServlet(webApplicationContext));
        context.addServletMappingDecoded("/*", "dispatcher");

        try {
            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }

    }
}
