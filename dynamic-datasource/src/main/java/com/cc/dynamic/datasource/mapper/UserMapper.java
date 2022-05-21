package com.cc.dynamic.datasource.mapper;

import com.cc.dynamic.datasource.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Desc TODO
 * @Author DELL
 * @Date 2022/5/21
 * @Version 1.0
 **/
public interface UserMapper {

    @Insert("insert into user (name) values (#{name})")
    void insert(User user);

    @Select("select * from user")
    List<User> select();
}
