package com.cc.consumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/08/21
 */
@Service
@Slf4j
public class CacheDemoService {

    @Autowired
    private RestTemplate restTemplate;


    /**
     * 同一个请求提供缓存。
     *
     * @param [name]
     * @author wangchen
     * @createDate 2020/8/21
     **/
    @HystrixCommand(fallbackMethod = "getUserFallback")
    @CacheResult(cacheKeyMethod = "genGetUserCacheKey")
    public String getUser(String name) {
        log.info("准备调用 demo-provider,获取用户{}详情", name);
        return restTemplate.getForEntity("http://demo-provider/user/get?name=" + name, String.class).getBody();
    }

    /**
     * 同一个请求删除缓存
     *
     * @param [name]
     * @author wangchen
     * @createDate 2020/8/21
     **/
    @HystrixCommand
    @CacheRemove(commandKey = "getUser",cacheKeyMethod = "genGetUserCacheKey")
    public void updateUser(String name){
        log.info("[updateUser][更新用户({})详情]", name);
    }

    public String getUserFallback(String name,Throwable throwable){
        log.info("[getUserFallback][name({}) exception({})]", name, ExceptionUtils.getRootCauseMessage(throwable));
        return "mock:user:"+name;
    }

    public String genGetUserCacheKey(String name){
        return "USER_"+name;
    }
}
