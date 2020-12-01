package com.cc.learn.concurrent;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 描述：     演示读写锁用法
 * 多线程共享读   ，写锁只能由一个线程获得。
 * 读读共享、其他都互斥（写写互斥、读写互斥、写读互斥）
 */

public class ReadWriteLockDemo {

    private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(

            false);

    private static final ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock

            .readLock();

    private static final ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock

            .writeLock();

    private static void read() {

        readLock.lock();

        try {

            System.out.println(Thread.currentThread().getName() + "得到读锁，正在读取");

            Thread.sleep(500);

        } catch (InterruptedException e) {

            e.printStackTrace();

        } finally {

            System.out.println(Thread.currentThread().getName() + "释放读锁");

            readLock.unlock();

        }

    }

    private static void write() {

        writeLock.lock();

        try {

            System.out.println(Thread.currentThread().getName() + "得到写锁，正在写入");

            Thread.sleep(500);

        } catch (InterruptedException e) {

            e.printStackTrace();

        } finally {

            System.out.println(Thread.currentThread().getName() + "释放写锁");

            writeLock.unlock();

        }

    }

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> read()).start();

        new Thread(() -> read()).start();

        new Thread(() -> write()).start();

        new Thread(() -> write()).start();

    }

}
