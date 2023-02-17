package com.handwrite;

/**
 * @author zhangpba
 * @description TODO
 * @date 2023/2/17
 */
public class JettyWebServer implements WebServer{
    @Override
    public void start() {
        System.out.println("启动 jetty!");
    }
}
