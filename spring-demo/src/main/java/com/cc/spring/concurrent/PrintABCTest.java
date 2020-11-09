package com.cc.spring.concurrent;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/11/06
 */
@Data
public class PrintABCTest {

    private static String flag = "a";
    private static Lock lock = new ReentrantLock();
    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();

    public static void main(String[] args) {
        new A().start();
        new B().start();
        new C().start();

    }

    static class A extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    if (flag.equals("a")) {
                        System.out.println("a");
                        flag = "b";
                    }
                    conditionB.signal();
                    conditionA.await();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class B extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    if (flag.equals("b")) {
                        System.out.println("b");
                        flag = "c";
                    }
                    conditionC.signal();
                    conditionB.await();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }


    static class C extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    if (flag.equals("c")) {
                        System.out.println("c");
                        flag = "a";
                    }
                    conditionA.signal();
                    conditionC.await();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }


}
