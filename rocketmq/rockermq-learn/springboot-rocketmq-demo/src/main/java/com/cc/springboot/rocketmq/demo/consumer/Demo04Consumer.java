package com.cc.springboot.rocketmq.demo.consumer;

import com.cc.springboot.rocketmq.demo.message.Demo04Message;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 模拟消费重试
 *
 * @author wangchen
 * @createDate 2020/8/28
 **/
@Component
@RocketMQMessageListener(
        topic = Demo04Message.TOPIC,
        consumerGroup = "demo04-consumer-group-" + Demo04Message.TOPIC
)
public class Demo04Consumer implements RocketMQListener<Demo04Message> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(Demo04Message message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        // <X> 注意，此处抛出一个 RuntimeException 异常，模拟消费失败
        throw new RuntimeException("我就是故意抛出一个异常");
    }

}