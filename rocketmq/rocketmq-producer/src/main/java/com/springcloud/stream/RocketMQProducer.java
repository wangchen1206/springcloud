package com.springcloud.stream;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @Author: cc
 * @Date: 2019/10/29 14:35
 */
public class RocketMQProducer {
    public static void main(String[] args) {
        try {
            DefaultMQProducer producer = new DefaultMQProducer("producer_group");
            producer.setNamesrvAddr("127.0.0.1:9876");
            producer.start();

            Message msg = new Message("test-topic", "tagStr", "message from rocketmq producer hello".getBytes());
            producer.send(msg);
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
