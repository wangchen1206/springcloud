package com.cc.springboot.rocketmq.demo.producer;

import com.cc.springboot.rocketmq.demo.message.Demo06Message;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 顺序发送,支持同步，异步，单向 三种顺序发送。
 *
 * @author wangchen
 * @createDate 2020/08/28
 */
@Component
public class Demo06Producer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public SendResult syncSendOrderly(Integer id){
        Demo06Message message = new Demo06Message();
        message.setId(id);
        return rocketMQTemplate.syncSendOrderly(Demo06Message.TOPIC,message,String.valueOf(id));
    }

    public void asyncSendOrderly(Integer id, SendCallback callback){
        Demo06Message message = new Demo06Message();
        message.setId(id);
        rocketMQTemplate.asyncSendOrderly(Demo06Message.TOPIC,message,String.valueOf(id),callback);
    }

    public void oneWaySendOrderly(Integer id){
        Demo06Message message = new Demo06Message();
        message.setId(id);
        rocketMQTemplate.sendOneWayOrderly(Demo06Message.TOPIC,message,String.valueOf(id));
    }
}
