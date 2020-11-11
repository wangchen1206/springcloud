package com.cc.spring.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁和非公平锁
 *
 * @author wangchen
 * @createDate 2020/11/11
 */
public class FairAndUnfair {

    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Thread thread[] = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread " + i);
        }
        for (int i = 0; i < 10; i++) {
            thread[i].start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Job implements Runnable {
    private PrintQueue printQueue;
    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }
    @Override
    public void run() {
        System.out.printf("%s: Going to print a job\n", Thread.currentThread().getName());
        printQueue.printJob(new Object());
        System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
    }
}
class PrintQueue {
    private final Lock queueLock = new ReentrantLock(false);
    public void printJob(Object document) {
        queueLock.lock();
        try {
            Long duration = (long) (Math.random() * 10000);
            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n",
                    Thread.currentThread().getName(), (duration / 1000));
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }
//        queueLock.lock();
//        try {
//            Long duration = (long) (Math.random() * 10000);
//            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n",
//                    Thread.currentThread().getName(), (duration / 1000));
//            Thread.sleep(duration);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            queueLock.unlock();
//        }
    }
}
