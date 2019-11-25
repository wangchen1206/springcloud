package com.cc.spring.design.factory;

/**
 * @Author: cc
 * @Date: 2019/11/22 15:04
 */
public class MailSender implements Sender {
    @Override
    public void send() {
        System.out.println("this is a mailSender");
    }
}
