package com.cc.spring.design.decorator;

/**
 * @Author: cc
 * @Date: 2019/11/25 16:08
 */
public class Source implements Sourceable {
    @Override
    public void method() {
        System.out.println("the original method");
    }
}
