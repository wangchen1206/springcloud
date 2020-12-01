package com.cc.learn.design.mediator;

/**
 * @Author: cc
 * @Date: 2019/12/6 14:06
 */
public abstract class User {
    private Mediator mediator;

    public User(Mediator mediator) {
        this.mediator = mediator;
    }

    public Mediator getMediator() {
        return mediator;
    }

    public abstract void work();
}
