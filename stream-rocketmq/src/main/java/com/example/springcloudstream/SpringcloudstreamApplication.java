package com.example.springcloudstream;

import com.alibaba.fastjson.support.spring.messaging.MappingFastJsonMessageConverter;
import com.example.springcloudstream.bindings.MyProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamMessageConverter;
import org.springframework.cloud.stream.annotation.StreamRetryTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@EnableBinding(MyProcessor.class)
@SpringBootApplication
public class SpringcloudstreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudstreamApplication.class, args);
    }


    /**
     * 使用Fastjson消息转换器转换消息
     * @return

    @Bean
    @StreamMessageConverter
    public MappingFastJsonMessageConverter mappingFastJsonMessageConverter() {
        return new MappingFastJsonMessageConverter();
    }
     */


    /**
     * 定义重试策略
     * 通过配置文件指定的重试配置都将失效。
     * @return
     */
//    @StreamRetryTemplate
//    public RetryTemplate retryTemplate(){
//        RetryTemplate retryTemplate = new RetryTemplate();
//        retryTemplate.setRetryPolicy(new SimpleRetryPolicy(2));
//        return retryTemplate;
//    }
}
