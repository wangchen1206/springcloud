package com.cc.spring.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/11/05
 */
@Slf4j
public class CustomRejectionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        log.info("队列已满。。。，提交任务线程执行任务");
        if (!executor.isShutdown()) {
            r.run();
        }
    }
}
