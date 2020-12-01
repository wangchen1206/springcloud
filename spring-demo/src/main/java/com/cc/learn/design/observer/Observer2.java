package com.cc.learn.design.observer;

/**
 * @Author: cc
 * @Date: 2019/11/26 16:00
 */
public class Observer2 implements Observer {
    @Override
    public void update() {
        System.out.println("observer2 has received");
    }
}
