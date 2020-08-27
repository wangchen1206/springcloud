package com.cc.springboot.rocketmq.demo.consumer;

import com.cc.springboot.rocketmq.demo.message.Demo01Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
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
        topic = Demo01Message.TOPIC,
        consumerGroup = "demo01-A-consumer-group-"+ Demo01Message.TOPIC
)
@Slf4j
public class Demo01AConsumer implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
