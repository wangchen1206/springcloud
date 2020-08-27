package com.example.springcloudstream.msg.sub;

import com.example.springcloudstream.bindings.MyProcessor;
import com.example.springcloudstream.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserConsumer {

    /**
     * Json Test
     * @param user
     */
    @StreamListener(MyProcessor.INPUT1)
    public void consumeUser(User user) {
        log.info("从Binding-{}收到User类型的消息-{}", MyProcessor.INPUT1, user);
    }

    /**
     * 以tag来区分不同的消息
     * @param message

    @StreamListener(MyProcessor.INPUT1)
    public void consumeUserTag(Message<String> message) {
        //String tag = (String) message.getHeaders().get(MessageConst.PROPERTY_TAGS);
        String tag = (String) message.getHeaders().get("rocketmq_TAGS");
        switch (tag){
            case "user":
                log.info("Tag is :"+"user");
                log.info("message is : "+message.getPayload());
                break;
            case "user1":
                log.info("Tag is :"+"user1");
                log.info("message is : "+message.getPayload());
                break;
            default:
                log.info("no tag matches!");
        }
    }
     */
    
}
