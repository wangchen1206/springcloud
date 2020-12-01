package com.cc.learn.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  所有线程等待 主线程执行完，再执行。
 *
 * @author wangchen
 * @createDate 2020/11/19
 **/
public class RunDemo2 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("运动员有5秒的准备时间");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = () -> {
                System.out.println(no + "号运动员准备完毕，等待裁判员的发令枪");
                try {
                    //线程等待
                    countDownLatch.await();
                    System.out.println(no + "号运动员开始跑步了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            service.submit(runnable);
        }
        Thread.sleep(5000);
        System.out.println("5秒准备时间已过，发令枪响，比赛开始！");
        //值变为1，唤醒其他线程。
        countDownLatch.countDown();
    }
}