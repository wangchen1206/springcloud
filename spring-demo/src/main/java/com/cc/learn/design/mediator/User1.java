package com.cc.learn.design.mediator;

/**
 * @Author: cc
 * @Date: 2019/12/6 14:09
 */
public class User1 extends User {

    public User1(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void work() {
        System.out.println("user1 exe");
    }
}
