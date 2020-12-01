package com.cc.learn.design.mediator;

/**
 * @Author: cc
 * @Date: 2019/12/6 14:11
 */
public class User2 extends User {

    public User2(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void work() {
        System.out.println("user2 exe");
    }
}
