package com.cc.spring.design.observer;

/**
 * @Author: cc
 * @Date: 2019/11/26 16:07
 */
public class MySubject extends AbstractSubject {
    @Override
    public void operation() {
        System.out.println("update self!");
        notifyAllObservers();
    }
}
