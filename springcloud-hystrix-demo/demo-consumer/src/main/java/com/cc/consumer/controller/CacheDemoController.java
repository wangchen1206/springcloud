package com.cc.consumer.controller;

import com.cc.consumer.service.CacheDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/08/21
 */
@RestController
@RequestMapping("/cache-demo")
public class CacheDemoController {

    @Autowired
    private CacheDemoService cacheDemoService;

    @GetMapping("/get_user")
    public String getUser(String name){
        String user = cacheDemoService.getUser(name);
        //测试是不是从缓存中获取
        String user1 = cacheDemoService.getUser(name);
        return cacheDemoService.getUser(name);
    }

    @GetMapping("/update_user")
    public String updateUser(String name){
        String user = cacheDemoService.getUser(name);
        cacheDemoService.updateUser(name);
        //测试不走缓存
        String user1 = cacheDemoService.getUser(name);
        return user1;

    }


}
