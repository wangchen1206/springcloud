package com.cc.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/08/26
 */
@SpringBootApplication
@EnableFeignClients
public class DemoConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoConsumerApplication.class,args);
    }
}
