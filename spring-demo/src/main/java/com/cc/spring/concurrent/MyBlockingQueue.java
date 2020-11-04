package com.cc.spring.concurrent;

import java.util.LinkedList;

/**
 * synchronized 实现 生产者-消费者
 *
 * @author wangchen
 * @createDate 2020/10/30
 */
public class MyBlockingQueue {
    private int maxSize;
    private LinkedList<Object> storage;

    public MyBlockingQueue(int size) {
        this.maxSize = size;
        storage = new LinkedList<>();
    }

    public synchronized void put() throws InterruptedException {
        while (storage.size() == maxSize) {
            wait();
        }
        storage.add(new Object());
        notifyAll();
    }

    public synchronized void take() throws InterruptedException {
        while (storage.size() == 0) {
            wait();
        }
        System.out.println(storage.remove());
        notifyAll();
    }
}
