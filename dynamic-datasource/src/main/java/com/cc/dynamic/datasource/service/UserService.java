package com.cc.dynamic.datasource.service;

import com.cc.dynamic.datasource.entity.User;

import java.util.List;

/**
 * @ClassName UserService
 * @Desc TODO
 * @Author DELL
 * @Date 2022/5/21
 * @Version 1.0
 **/
public interface UserService {

    void insert(User user);

    List<User> select();
}
