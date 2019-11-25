package com.cc.spring.design.bridge;

/**
 * @Author: cc
 * @Date: 2019/11/25 17:46
 */
public class MyBridge extends Bridge {
    @Override
    public void method(){
        getSource().method();
    }
}
