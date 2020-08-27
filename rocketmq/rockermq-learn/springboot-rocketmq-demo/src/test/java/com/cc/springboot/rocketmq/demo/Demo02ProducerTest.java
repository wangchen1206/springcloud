package com.cc.springboot.rocketmq.demo;

import com.cc.springboot.rocketmq.demo.producer.Demo02Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
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
public class Demo02ProducerTest {

    @Autowired
    private Demo02Producer producer;

    @Test
    public void testSyncBatchSend() throws InterruptedException {
        List<Integer> ids = Arrays.asList(1,2,3);
        SendResult sendResult = producer.batchSend(ids);
        log.info("[testSendBatch][发送编号：[{}] 发送结果：[{}]]", ids, sendResult);

        //阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}
