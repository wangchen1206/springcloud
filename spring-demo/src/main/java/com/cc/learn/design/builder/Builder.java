package com.cc.learn.design.builder;

import com.cc.learn.design.factory.MailSender;
import com.cc.learn.design.factory.Sender;
import com.cc.learn.design.factory.SmsSender;

import java.util.ArrayList;
import java.util.List;

/**
 * 建造者模式
 * 将很多功能集合在一起，创建符合对象，多个部分。
 *
 * @Author: cc
 * @Date: 2019/11/22 14:55
 */
public class Builder {

    private List<Sender> list = new ArrayList<>();

    public void produceMailSenders(int count){
        for (int i = 0; i < count; i++) {
            list.add(new MailSender());
        }
    }

    public void produceSmsSenders(int count){
        for (int i = 0; i < count; i++) {
            list.add(new SmsSender());
        }
    }
}
