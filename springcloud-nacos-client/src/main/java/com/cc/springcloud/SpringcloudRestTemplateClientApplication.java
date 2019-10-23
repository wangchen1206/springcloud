//package com.cc.springcloud;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//@SpringBootApplication
//@EnableDiscoveryClient
//public class SpringcloudRestTemplateClientApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(SpringcloudRestTemplateClientApplication.class, args);
//    }
//
///**
// * 注入负载均衡的restTemplate
// */
//@Bean
//@LoadBalanced
//    public RestTemplate restTemplate(){
//        return new RestTemplate();
//    }
//
//    @RestController
//    @Slf4j
//    static class TestController {
//
//        @Autowired
//        RestTemplate restTemplate;
//
//        @GetMapping("/test")
//        public String test() {
//            String result = restTemplate.getForObject("http://alibaba-nacos-discovery-server/hello?name="+"restTemplate", String.class);
//            return "Return : " + result;
//        }
//    }
//
//}
