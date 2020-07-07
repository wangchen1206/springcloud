package com.example.springcloudstream;

import com.alibaba.fastjson.JSON;
import com.example.springcloudstream.entity.User;
import com.example.springcloudstream.msg.pub.SourceProducer;
import com.example.springcloudstream.msg.pub.UserProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringcloudstreamApplicationTests {

    @Autowired
    private SourceProducer sourceProducer;

    @Autowired
    private UserProducer userProducer;

    @Test
    public void test(){
        sourceProducer.sendMessage("hello cc");
    }

    @Test
    public void test1(){
        User u = new User();
        u.setName("cc");
        u.setCreateTime(new Date());
        u.setId(1l);
        userProducer.userProduce(u);
    }

    @Test
    public void test2(){
        User u = new User();
        u.setName("cc");
        u.setCreateTime(new Date());
        u.setId(1l);
        String string = JSON.toJSONString(u);
        System.out.println(string);
    }

}
