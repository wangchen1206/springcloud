package com.example.springcloudstream.msg.sub;

import com.example.springcloudstream.bindings.MyProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

/**
 * @Author: ck
 * @Date: 2020/7/6 8:57
 */
@Component
@Slf4j
public class InputThirdConsumer {

    @StreamListener(MyProcessor.INPUT2)
    public void inputThird(Message<String> message){
        String payload = message.getPayload();
        MessageHeaders headers = message.getHeaders();
        log.info("message : "+payload +"---"+" headers: "+headers+ " --- tag: "+headers.get("tag"));
    }
}
