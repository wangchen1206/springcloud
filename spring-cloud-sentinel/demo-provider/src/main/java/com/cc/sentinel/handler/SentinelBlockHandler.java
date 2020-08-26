package com.cc.sentinel.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * 还不清楚怎么使用 @SentinelResource(blockHandlerClass)
 *
 * @author wangchen
 * @createDate 2020/08/26
 */
public class SentinelBlockHandler {

    public static String annotationsDemo(Integer id, BlockException ex){
        return "block: "+ex.getClass().getSimpleName();
    }
}
