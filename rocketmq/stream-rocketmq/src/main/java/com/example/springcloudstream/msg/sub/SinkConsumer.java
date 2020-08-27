package com.example.springcloudstream.msg.sub;

import com.alibaba.fastjson.JSONObject;
import com.example.springcloudstream.bindings.MyProcessor;
import com.example.springcloudstream.entity.User;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @Author: ck
 * @Date: 2020/7/3 12:43
 */
@Component
public class SinkConsumer {


    /**
     * 边接收，边发送
     * @param message
     */
    @StreamListener(MyProcessor.INPUT)
    @SendTo(MyProcessor.OUTPUT1)
//    @Output(MyProcessor.OUTPUT1)
    public User inputConsumer(String message){
        System.out.println(String.format("从Binding-{%s}收到信息-{%s}", MyProcessor.INPUT, message));
        return JSONObject.parseObject(message, User.class);
    }
}
