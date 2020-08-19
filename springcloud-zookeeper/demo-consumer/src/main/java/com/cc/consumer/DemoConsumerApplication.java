package com.cc.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/08/18
 */
@SpringBootApplication
public class DemoConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoConsumerApplication.class, args);
    }

    @Configuration
    public class RestTemplateConfiguration {

        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }

    }

    @RestController
    static class TestController {

        @Autowired
        private RestTemplate restTemplate;

        @Autowired
        private DiscoveryClient discoveryClient;

        @Autowired
        private LoadBalancerClient loadBalancerClient;


        @GetMapping("/hello")
        public String hello(String name) {
            //获得服务 'demo-provider' 一个实例
            ServiceInstance serviceInstance = null;
            if (true) {
                //获取服务列表
                List<ServiceInstance> instances = discoveryClient.getInstances("demo-provider");
                //选择第一个
                serviceInstance = instances.size() > 0 ? instances.get(0) : null;
            } else {
                serviceInstance = loadBalancerClient.choose("demo-provider");
            }
            if (serviceInstance == null) {
                throw new IllegalStateException("获取不到实例");
            }
            // 直接使用 RestTemplate 调用服务 `demo-provider`
            String targetUrl = serviceInstance.getUri() + "/echo?name=" + name;
            //解析服务名
            String response = restTemplate.getForObject(targetUrl, String.class);
            // 返回结果
            return "consumer:" + response;
        }

    }
}
