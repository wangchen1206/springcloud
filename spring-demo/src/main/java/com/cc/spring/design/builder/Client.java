package com.cc.spring.design.builder;

/**
 * @Author: cc
 * @Date: 2019/11/22 15:22
 */
public class Client {
    public static void main(String[] args) {
        Builder builder = new Builder();
        builder.produceMailSenders(10);
    }
}
