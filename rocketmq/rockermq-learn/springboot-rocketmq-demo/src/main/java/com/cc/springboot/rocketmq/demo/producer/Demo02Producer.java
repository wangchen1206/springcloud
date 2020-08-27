package com.cc.springboot.rocketmq.demo.producer;

import com.cc.springboot.rocketmq.demo.message.Demo02Message;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/08/27
 */
@Component
public class Demo02Producer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 批量同步发送 最终需要构建成Spring Messaging的Message 类型
     *
     * @param ids
     * @author wangchen
     * @createDate 2020/8/27
     **/
    public SendResult batchSend(Collection<Integer> ids){
        List<Message<Demo02Message>> messages = ids.stream().map(id -> {
            Demo02Message demo02Message = new Demo02Message();
            demo02Message.setId(id);
            //构建Spring Messaging定义的 Message,以及设置 key
            return MessageBuilder.withPayload(demo02Message).setHeader(RocketMQHeaders.KEYS,id).build();
        }).collect(Collectors.toList());
        //同步批量发送，topic和tag
        return rocketMQTemplate.syncSend(Demo02Message.TOPIC_TAG,messages,30*3000L);
    }
}
