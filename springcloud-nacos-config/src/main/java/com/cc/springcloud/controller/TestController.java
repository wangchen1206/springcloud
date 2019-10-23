package com.cc.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: cc
 * @Date: 2019/10/23 18:28
 */
@RestController
@RefreshScope
public class TestController {

    @Value("${user.name}")
    private String username;

    @Value("${user.age}")
    private Integer age;

    @GetMapping("/test")
    public String test() {
        System.out.println("---------------------");
        return "the username is :" + username + ",age is :" + age;
    }
}
