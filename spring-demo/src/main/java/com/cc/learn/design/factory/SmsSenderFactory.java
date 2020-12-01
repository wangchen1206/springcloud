package com.cc.learn.design.factory;

/**
 * @Author: cc
 * @Date: 2019/11/22 15:06
 */
public class SmsSenderFactory implements Producer {
    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
