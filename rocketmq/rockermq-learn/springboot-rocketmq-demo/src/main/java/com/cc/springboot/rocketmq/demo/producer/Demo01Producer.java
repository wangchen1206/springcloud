package com.cc.springboot.rocketmq.demo.producer;

import com.cc.springboot.rocketmq.demo.message.Demo01Message;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 使用RocketMQTemplate 实现三种消息发送方式
 *
 * @author wangchen
 * @createDate 2020/08/27
 */
@Component
public class Demo01Producer {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 同步发送消息
     *
     * @param [id]
     * @author wangchen
     * @createDate 2020/8/27
     **/
    public SendResult syncSend(Integer id){
        Demo01Message message = new Demo01Message();
        message.setId(id);
        //同步发送消息
        return rocketMQTemplate.syncSend(Demo01Message.TOPIC,message);
    }

    /**
     * 异步发送消息
     *
     * @param [id, callback]
     * @author wangchen
     * @createDate 2020/8/27
     **/
    public void asyncSend(Integer id, SendCallback callback){
        Demo01Message message = new Demo01Message();
        message.setId(id);
        rocketMQTemplate.asyncSend(Demo01Message.TOPIC,message,callback);
    }

    /**
     * 单向发送消息
     *
     * @param [id]
     * @author wangchen
     * @createDate 2020/8/27
     **/
    public void oneWaySend(Integer id){
        Demo01Message message = new Demo01Message();
        message.setId(id);
        rocketMQTemplate.sendOneWay(Demo01Message.TOPIC,message);
    }
}
