package com.cc.dynamic.datasource.service.impl;

import com.cc.dynamic.datasource.config.annotation.DataSource;
import com.cc.dynamic.datasource.entity.User;
import com.cc.dynamic.datasource.mapper.UserMapper;
import com.cc.dynamic.datasource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Desc TODO
 * @Author DELL
 * @Date 2022/5/21
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @DataSource("cc2")
    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

//    @DataSource("cc")
    @Override
    public List<User> select() {
        return userMapper.select();
    }
}
