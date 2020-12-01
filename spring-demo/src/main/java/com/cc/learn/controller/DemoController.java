package com.cc.learn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: cc
 * @Date: 2019/11/12 15:06
 */
@RestController
public class DemoController {

    @GetMapping("/test")
    public Map test(@RequestParam("username") String username){
        Map result = new HashMap();
        System.out.println("test---"+username);
        result.put("username",username);
        return result;
    }
}
