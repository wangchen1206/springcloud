package com.cc.spring.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用 ThreadLocal保存每个线程的SimpleDateFormat
 *
 * @param
 * @author wangchen
 * @createDate 2020/11/17
 **/
public class ThreadLocalDemo06 {
    public static ExecutorService threadPool = Executors.newFixedThreadPool(16);
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            threadPool.submit(() -> {
                System.out.println(Thread.currentThread().getName()+"---"+ThreadSafeFormatter.dateFormatThreadLocal);
                System.out.println(Thread.currentThread().getName()+"---"+ThreadSafeFormatter1.dateFormatThreadLocal1);
                String date = new ThreadLocalDemo06().date(finalI);
//                System.out.println(date);
            });
        }
        //避免内存泄漏
        ThreadSafeFormatter.dateFormatThreadLocal.remove();
        ThreadSafeFormatter1.dateFormatThreadLocal1.remove();
        threadPool.shutdown();
    }
    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        SimpleDateFormat dateFormat = ThreadSafeFormatter.dateFormatThreadLocal.get();
        Date date1 = ThreadSafeFormatter1.dateFormatThreadLocal1.get();
//        System.out.println(Thread.currentThread().getName()+"---"+dateFormat.toPattern());
//        System.out.println(Thread.currentThread().getName()+"---"+date1);
        return dateFormat.format(date);
    }
}
class ThreadSafeFormatter {
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("mm:ss"));
}

class ThreadSafeFormatter1 {
    public static ThreadLocal<Date> dateFormatThreadLocal1 = ThreadLocal.withInitial(() -> new Date());
}