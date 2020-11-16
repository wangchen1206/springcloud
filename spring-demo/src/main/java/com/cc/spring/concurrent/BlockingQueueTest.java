package com.cc.spring.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列 各种方法测试
 *
 * 抛出异常：add、remove、element
 * 返回结果但不抛出异常：offer、poll、peek
 * 阻塞：put、take
 *
 * @author wangchen
 * @createDate 2020/11/13
 */
public class BlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
//        addTest();
//        removeTest();
//        elementTest();
//        pollTest();
        peekTest();
    }
    private static void addTest() {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(2);
        blockingQueue.add(1);
        blockingQueue.add(1);
        blockingQueue.add(1);
    }

    private static void removeTest() {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(2);
        blockingQueue.add(1);
        blockingQueue.add(1);
        blockingQueue.remove();
        blockingQueue.remove();
        blockingQueue.remove();
    }

    private static void elementTest() {
        ArrayBlockingQueue<Integer> blockingQueue = new     ArrayBlockingQueue<Integer>(2);
        blockingQueue.element();
    }

    private static void pollTest() {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(3);
        blockingQueue.offer(1);
        blockingQueue.offer(2);
        blockingQueue.offer(3);
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    private static void peekTest() throws InterruptedException {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(2);
//        blockingQueue.put(null);
        System.out.println(blockingQueue.peek());
    }

}
