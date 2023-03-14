package com.keccoredump.demo.service;

import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class PortListener implements ApplicationListener<ServletWebServerInitializedEvent> {

    @Override
    public void onApplicationEvent(final ServletWebServerInitializedEvent event) {
        int port = event.getWebServer().getPort();

        System.out.println("************************************");
        System.out.printf("Web server is running on %d\n", port);
        System.out.println("************************************");
    }
}