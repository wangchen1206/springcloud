package com.cc.spring.concurrent;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**

 * 描述：     演示读锁不插队
 * 当有多个线程持有读锁，在有线程等待写锁的时候，不允许读锁插队
 *

 */

public class ReadLockJumpQueue {

    private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    private static final ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock

            .readLock();

    private static final ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock

            .writeLock();

    private static void read() {

        readLock.lock();

        try {

            System.out.println(Thread.currentThread().getName() + "得到读锁，正在读取");

            Thread.sleep(2000);

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

            Thread.sleep(2000);

        } catch (InterruptedException e) {

            e.printStackTrace();

        } finally {

            System.out.println(Thread.currentThread().getName() + "释放写锁");

            writeLock.unlock();

        }

    }

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> read(),"Thread-2").start();
        Thread.sleep(100);

        new Thread(() -> read(),"Thread-4").start();

        Thread.sleep(100);
        new Thread(() -> write(),"Thread-3").start();

        Thread.sleep(100);
        new Thread(() -> read(),"Thread-5").start();

    }

}
