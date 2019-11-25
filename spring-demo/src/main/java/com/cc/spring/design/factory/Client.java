package com.cc.spring.design.factory;

/**
 * @Author: cc
 * @Date: 2019/11/22 15:11
 */
public class Client {
    public static void main(String[] args) {
        Producer smsProducer = new SmsSenderFactory();
        Sender smsSender = smsProducer.produce();
        smsSender.send();
    }
}
