package com.cc.spring.design.observer;

/**
 * @Author: cc
 * @Date: 2019/11/26 15:59
 */
public class Observer1 implements Observer {
    @Override
    public void update() {
        System.out.println("observer1 has received!");
    }
}
