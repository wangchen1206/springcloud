package com.cc.learn.cache;

/**
 * 自定义缓存测试
 *
 * @author wangchen
 * @createDate 2020/12/01
 */
public class MyCacheTest {
    public static void main(String[] args) {
        CacheUtils cacheUtils = new CacheUtils();
        cacheUtils.put("key","老王",10);
        //查询缓存
        String val = (String) cacheUtils.get("key");
        System.out.println(val);
        //查询不存在的缓存
        String xxx = (String) cacheUtils.get("xxx");
        System.out.println(xxx);
    }
}
