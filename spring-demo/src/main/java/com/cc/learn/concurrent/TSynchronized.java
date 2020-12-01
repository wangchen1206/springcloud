package com.cc.learn.concurrent;

public class TSynchronized implements Runnable {

    static volatile int i = 0;  //内存可见性

    public void increase() {
        i++;

//        temp = i+1;                i变量的值加1操作
//        i = temp;
    }


    @Override
    public synchronized void run() {
        for (i = 0; i < 100000; i++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        TSynchronized tSynchronized = new TSynchronized();
        Thread aThread = new Thread(tSynchronized);
        Thread bThread = new Thread(tSynchronized);
        aThread.start();
        bThread.start();
        aThread.join();
        bThread.join();

        System.out.println("i = " + i);
    }
}