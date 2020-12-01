package com.cc.learn.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 使用BlockQueue来实现生产者-消费者模式
 *
 * @author wangchen
 * @createDate 2020/10/30
 */
public class BlockQueueConsumerProducer {
    public static void main(String[] args) {
        BlockingQueue queue = new ArrayBlockingQueue(10);
        Runnable producer = ()->{
          while (true){
              try {
                  queue.put(new Object());
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
        };
        new Thread(producer).start();
        new Thread(producer).start();

        Runnable consumer = ()->{
            while (true){
                try {
                    queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(consumer).start();
        new Thread(consumer).start();
    }
}
