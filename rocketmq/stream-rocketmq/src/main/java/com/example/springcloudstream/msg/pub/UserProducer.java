package com.example.springcloudstream.msg.pub;

import com.example.springcloudstream.bindings.MyProcessor;
import com.example.springcloudstream.entity.User;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ck
 * @Date: 2020/7/3 12:51
 */
@Component
public class UserProducer {

    @Autowired
    @Qualifier(MyProcessor.OUTPUT1)
    private MessageChannel messageChannel;

    public void userProduce(User user){
        Map<String, Object> headers = new HashMap<>();
        headers.put(MessageConst.PROPERTY_TAGS, "userTag");
        MessageHeaders messageHeaders = new MessageHeaders(headers);
        Message<User> message = MessageBuilder.createMessage(user, messageHeaders);
        this.messageChannel.send(message);
    }
}
