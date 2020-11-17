package com.cc.spring.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程不安全 SimpleDateFormat
 *
 * @param
 * @author wangchen
 * @createDate 2020/11/17
 **/
public class ThreadLocalDemo04 {
    public static ExecutorService threadPool = Executors.newFixedThreadPool(16);
    static SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(() -> {
                String date = new ThreadLocalDemo04().date(finalI);
                System.out.println(date);
            });
        }
        threadPool.shutdown();
    }
    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        return dateFormat.format(date);
    }
}