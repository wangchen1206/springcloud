package com.cc.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/08/18
 */
@SpringBootApplication
public class DemoConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoConsumerApplication.class,args);
    }

    @Configuration
    public class RestTemplateConfiguration {

        @Bean
        @LoadBalanced
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }

        //这个不加@LoadBalanced，不会解析服务名
        @Bean
        public RestTemplate restTemplate1(){
            return new RestTemplate();
        }

    }

    @RestController
    static class TestController {

        @Autowired
        private RestTemplate restTemplate;

        @Resource(name = "restTemplate1")
        private RestTemplate restTemplate1;

        @Autowired
        private LoadBalancerClient loadBalancerClient;

        @GetMapping("/hello")
        public String hello(String name) {
            // 获得服务 `demo-provider` 的一个实例
            ServiceInstance instance = loadBalancerClient.choose("demo-provider");
            // 发起调用
            String targetUrl = instance.getUri() + "/echo?name=" + name;
            //不解析服务名
            String response = restTemplate1.getForObject(targetUrl, String.class);
            // 返回结果
            return "consumer:" + response;
        }

        @GetMapping("/hello02")
        public String hello02(String name) {
            // 直接使用 RestTemplate 调用服务 `demo-provider`
            String targetUrl = "http://demo-provider2/echo?name=" + name;
            //解析服务名
            String response = restTemplate.getForObject(targetUrl, String.class);
            // 返回结果
            return "consumer:" + response;
        }

    }
}
