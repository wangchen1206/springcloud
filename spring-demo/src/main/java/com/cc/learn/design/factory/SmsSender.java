package com.cc.learn.design.factory;

/**
 * @Author: cc
 * @Date: 2019/11/22 15:03
 */
public class SmsSender implements Sender {


    @Override
    public void send() {
        System.out.println("this is a smsSender");
    }
}
