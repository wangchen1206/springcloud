package com.cc.springboot.rocketmq.demo;

import com.cc.springboot.rocketmq.demo.producer.Demo01Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/08/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RocketmqDemoApplication.class)
@Slf4j
public class Demo01ProducerTest {

    @Autowired
    private Demo01Producer producer;

    @Test
    public void testSyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis()/1000);
        SendResult result = producer.syncSend(id);
        log.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);

        //阻塞等待,保证消费
        new CountDownLatch(1).await();
    }

    @Test
    public void testAsyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis()/1000);
        producer.asyncSend(id, new SendCallback() {
            @Override
            public void onSuccess(SendResult result) {
                log.info("[testASyncSend][发送编号：[{}] 发送成功，结果为：[{}]]", id, result);
            }

            @Override
            public void onException(Throwable e) {
                log.info("[testASyncSend][发送编号：[{}] 发送异常]]", id, e);
            }
        });
        //阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

    @Test
    public void testOneWaySend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis()/1000);
        producer.oneWaySend(id);
        log.info("[testOneWaySend][发送编号：[{}] 发送完成]", id);

        //阻塞等待,保证消费
        new CountDownLatch(1).await();
    }

}
