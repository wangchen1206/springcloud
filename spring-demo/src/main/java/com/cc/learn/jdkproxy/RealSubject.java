package com.cc.learn.jdkproxy;

/**
 * 真实主题类
 * @Author: cc
 * @Date: 2019/11/14 14:52
 */
public class RealSubject implements Subject{
    @Override
    public void doSomething() {
        System.out.println("RealSubject do something");
    }
}
