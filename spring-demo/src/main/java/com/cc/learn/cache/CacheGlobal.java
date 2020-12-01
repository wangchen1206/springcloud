package com.cc.learn.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 全局缓存对象
 *
 * @author wangchen
 * @createDate 2020/12/1
 **/
public class CacheGlobal {
    // 全局缓存对象
    public static ConcurrentMap<String, MyCache> concurrentMap = new ConcurrentHashMap<>();
}