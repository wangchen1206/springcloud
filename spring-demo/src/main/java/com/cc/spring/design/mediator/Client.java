package com.cc.spring.design.mediator;

/**
 * @Author: cc
 * @Date: 2019/12/6 14:12
 */
public class Client {
    public static void main(String[] args) {
        Mediator mediator = new MyMediator();
        mediator.craeteMediator();
        mediator.workAll();
    }
}
