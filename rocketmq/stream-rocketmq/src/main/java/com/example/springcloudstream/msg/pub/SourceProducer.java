package com.example.springcloudstream.msg.pub;

import com.example.springcloudstream.bindings.MyProcessor;
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
 * @Date: 2020/7/3 12:25
 */
@Component
public class SourceProducer {

//    @Autowired
//    private MyProcessor myProcessor;

    @Autowired
    @Qualifier(MyProcessor.OUTPUT)
    private MessageChannel messageChannel;

    public void sendMessage(String msg){
        String payload = msg;
        Map<String,Object> headers = new HashMap<>();
        headers.put(MessageConst.PROPERTY_TAGS,"testTag");
        MessageHeaders messageHeaders = new MessageHeaders(headers);
        Message<String> message = MessageBuilder.createMessage(payload, messageHeaders);
//        this.myProcessor.output().send(message);
        this.messageChannel.send(message);
    }
}
