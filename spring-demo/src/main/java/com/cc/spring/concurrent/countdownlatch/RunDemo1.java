package com.cc.spring.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 主线程等待 所有线程执行完毕，再继续往下执行
 *
 * @author wangchen
 * @createDate 2020/11/19
 **/
public class RunDemo1 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = () -> {
                try {
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println(no + "号运动员完成了比赛。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            };
            service.submit(runnable);
        }
        System.out.println("等待5个运动员都跑完.....");
        latch.await();
        service.shutdown();
        System.out.println("所有人都跑完了，比赛结束。");
    }
}