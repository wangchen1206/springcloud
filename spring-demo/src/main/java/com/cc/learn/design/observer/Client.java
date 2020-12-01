package com.cc.learn.design.observer;

/**
 * @Author: cc
 * @Date: 2019/11/26 19:19
 */
public class Client {

    public static void main(String[] args) {
        Subject subject = new MySubject();
        subject.add(new Observer1());
        subject.add(new Observer2());
        subject.operation();
    }

}
