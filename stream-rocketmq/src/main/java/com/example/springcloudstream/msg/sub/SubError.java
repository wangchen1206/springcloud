package com.example.springcloudstream.msg.sub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.stereotype.Component;

/**
 * 在接收消息时，如果消息处理失败，Spring Cloud会把失败的消息转到名为<destination>.<group>.errors的Channel，
 * 并可通过@ServiceActivator方法进行接收。比如有如下Binding定义。
 *
 * @Author: ck
 * @Date: 2020/7/3 16:21
 */
@Component
@Slf4j
public class SubError {


    /**
     * 在接收消息时，如果消息处理失败，Spring Cloud会把失败的消息转到名为<destination>.<group>.errors的Channel，
     * 并可通过@ServiceActivator方法进行接收。比如有如下Binding定义。
     * @param message
     */
//    @ServiceActivator(inputChannel = "test-topic1.test-group1.errors")
//    public void handleConsumerUserError(ErrorMessage message){
//        log.info("收到处理失败的消息{}", message.getOriginalMessage());
//    }


    /**
     * 统一的处理所有的消息消费异常的入口则可以定义一个Binding名为errorChannel的@StreamListener方法
     *
     * @param message
     */
    @StreamListener("errorChannel")
    public void handleErrors(ErrorMessage message){
        log.info("默认的消息失败处理器收到处理失败的消息：{}，headers: {}",message.getOriginalMessage(),message.getHeaders());
    }
}
