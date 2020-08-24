package com.cc.hystrix.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/08/24
 */
@SpringBootApplication
@EnableCircuitBreaker
@EnableFeignClients
public class HystrixFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixFeignApplication.class,args);
    }

//    @Bean
//    public RestTemplate restTemplate(){
//        return new RestTemplate();
//    }

}
