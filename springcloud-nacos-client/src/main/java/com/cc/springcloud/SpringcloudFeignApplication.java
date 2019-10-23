package com.cc.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
//注解开启扫描spring cloud Feign 客户端的功能
@EnableFeignClients
public class SpringcloudFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudFeignApplication.class, args);
    }


    @RestController
    @Slf4j
    static class TestController {

        /**
         * 注入接口实现
         */
        @Autowired
        Client client;

        @GetMapping("/test")
        public String test() {
            /**
             * 调用hello方法来触发对服务提供方的调用
             */
            return client.hello("FeignClient");
        }
    }


    /**
     * 创建Feign客户端接口定义
     * 使用@FeignClient注解来指定这个接口所要调用的服务名称
     */
    @FeignClient("alibaba-nacos-discovery-server")
    interface Client{

        /**
         * 使用springmvc 注解绑定服务提供方的Rest接口
         * @param name
         * @return
         */
        @GetMapping("hello")
        String hello(@RequestParam String name);
    }

}
