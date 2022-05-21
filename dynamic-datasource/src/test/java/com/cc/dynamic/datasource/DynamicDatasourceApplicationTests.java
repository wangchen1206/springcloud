package com.cc.dynamic.datasource;

import com.cc.dynamic.datasource.entity.User;
import com.cc.dynamic.datasource.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DynamicDatasourceApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        List<User> select = userService.select();
        System.out.println(select);

        User user = new User();
        user.setName("111");
        userService.insert(user);
    }


}
