package com.cc.spring.design.proxy;

/**
 * @Author: cc
 * @Date: 2019/11/25 16:34
 */
public class Source implements Sourceable {
    @Override
    public void method() {
        System.out.println("the original method!");
    }
}
