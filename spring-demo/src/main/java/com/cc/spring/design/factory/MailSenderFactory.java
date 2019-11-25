package com.cc.spring.design.factory;

/**
 * @Author: cc
 * @Date: 2019/11/22 15:06
 */
public class MailSenderFactory implements Producer {
    @Override
    public Sender produce() {
        return new MailSender();
    }
}
