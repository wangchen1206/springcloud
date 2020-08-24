package com.cc.hystrix.feign.controller;

import com.cc.hystrix.feign.user.UserServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/08/24
 */
@RestController
@RequestMapping("/feign-demo")
public class UserController {

    @Autowired
    private UserServiceFeignClient userServiceFeignClient;

    @GetMapping("/getUser")
    public String getUser(String name){
        return userServiceFeignClient.getUser(name);
    }
}
