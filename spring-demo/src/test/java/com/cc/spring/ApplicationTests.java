package com.cc.spring;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cc.spring.entity.User;
import com.cc.spring.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private IUserService userService;

    @Test
    void contextLoads() {
        System.out.println(11);
        User cc = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getUsername, "cc"));
        System.out.println(cc.getUsername()+"---"+cc.getUserInfor());

    }

}
