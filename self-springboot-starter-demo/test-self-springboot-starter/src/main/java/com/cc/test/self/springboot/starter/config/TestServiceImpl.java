package com.cc.test.self.springboot.starter.config;

import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @ClassName TestServiceImpl
 * @Desc TODO
 * @Author DELL
 * @Date 2022/5/2
 * @Version 1.0
 **/
@Service("testService")
public class TestServiceImpl implements TestService{

    /**
     * @Author wangchen
     * @Description //TODO
     * @Date 2022/5/2 13:37
     * @Param []
     * @return void
     **/
    @Override
    public void hello() {
        System.out.println(1);
    }

    public static void main(String[] args) {
        String a = new String("A");
        String a1 = new String("A");
        System.out.println(a == a1);
        System.out.println(a.equals(a1));
        System.out.println(a.hashCode());
        System.out.println(a1.hashCode());

        HashMap<String, String> map = new HashMap<>();
        map.put(a,"1");
        map.put(a1,"2");
        System.out.println(map.size());
        System.out.println(map.keySet());


    }
}
