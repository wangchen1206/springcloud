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
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//@SpringBootApplication
//@EnableDiscoveryClient
//public class SpringcloudWebClientClientApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(SpringcloudWebClientClientApplication.class, args);
//    }
//
//    /**
//     * 注入 负载均衡的webclient
//     *
//     * @return
//     */
//    @Bean
//    @LoadBalanced
//    public WebClient.Builder loadBalancedWebClientBuilder() {
//        return WebClient.builder();
//    }
//
//    @RestController
//    @Slf4j
//    static class TestController {
//
//        @Autowired
//        private WebClient.Builder webClientBuilder;
//
//        @GetMapping("/test")
//        public Mono<String> test() {
//            Mono<String> result = webClientBuilder.build()
//                    .get()
//                    .uri("http://hello-service/hello?name="+"webClient")
//                    .retrieve()
//                    .bodyToMono(String.class);
//            return result;
//        }
//    }
//
//}
