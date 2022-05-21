package com.cc.server.springboot.autoconfigure;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/08/20
 */
@Configuration  //声明配置类
@EnableConfigurationProperties(CCServerProperties.class) //使 CCServerProperties 配置属性类生效
public class CCServerAutoConfiguration {
    private Logger logger = LoggerFactory.getLogger(CCServerAutoConfiguration.class);

    @Bean
    @ConditionalOnClass(HttpServer.class)
    public HttpServer httpServer(CCServerProperties ccServerProperties) throws IOException {
        //创建HttpServer对象，并启动
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(ccServerProperties.getPort()),0);
        //创建path对应的handler
        httpServer.createContext("/test", (exchange)->{
            String response = "hello world";
            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });
        httpServer.start();
        logger.info("[httpServer][启动服务器成功，端口为:{}]",ccServerProperties.getPort());

        //返回
        return httpServer;
    }

}
