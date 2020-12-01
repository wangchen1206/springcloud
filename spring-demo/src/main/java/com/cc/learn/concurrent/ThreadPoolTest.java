package com.cc.learn.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/11/05
 */
@AllArgsConstructor
@Data
public class ThreadPoolTest implements Runnable{

    private String name;

    @SneakyThrows
    @Override
    public void run() {
        //测试自定义拒绝策略
        Thread.sleep(3000);
        System.out.println("task name:"+this.name+", task执行 "+Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        int corePoolSize = 5;
        int maximumPoolSize = 10;
        long keepAliveTime = 10;
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        //自定义线程池
        ThreadFactoryBuilder builder = new ThreadFactoryBuilder();
        ThreadFactory threadFactory1 = builder.setNameFormat("test-pool-%d").build();

        RejectedExecutionHandler handler = new CustomRejectionHandler();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), threadFactory1, handler);
        List<ThreadPoolTest> tasks = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            tasks.add(new ThreadPoolTest("test_"+i));
        }
        tasks.forEach(threadPoolExecutor::submit);
        threadPoolExecutor.shutdown();



    }
}
