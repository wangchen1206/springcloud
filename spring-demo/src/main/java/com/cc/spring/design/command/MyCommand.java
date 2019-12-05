package com.cc.spring.design.command;

/**
 * @Author: cc
 * @Date: 2019/12/5 15:57
 */
public class MyCommand implements Command {

    private Receiver receiver;

    public MyCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void exe() {
        this.receiver.action();
    }
}
