package com.cc.springboot.rocketmq.demo.consumer;

import com.cc.springboot.rocketmq.demo.message.Demo02Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/08/27
 */
@Component
@RocketMQMessageListener(
        topic = Demo02Message.TOPIC,
        consumerGroup = "demo02-consumer-group-" + Demo02Message.TOPIC
)
@Slf4j
public class Demo2Consumer implements RocketMQListener<Demo02Message> {
    @Override
    public void onMessage(Demo02Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
