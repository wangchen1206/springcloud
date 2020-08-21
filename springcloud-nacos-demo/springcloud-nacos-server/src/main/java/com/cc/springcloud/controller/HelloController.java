package com.cc.springcloud.controller;

import com.cc.springcloud.entity.User;
import com.cc.springcloud.entity.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * <Description>
 *
 * @author wangchen
 * @createDate 2020/07/24
 */
@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/get")
    public User get(@RequestParam("id") Integer id){
        log.info("get--------");
        return User.builder().id(id)
                .username("cc").build();

    }

    @PostMapping("/add")
    public Integer add(@RequestBody UserDTO user){
        log.info("add--------");
        return (int)System.currentTimeMillis()/1000;
    }
}
