package com.cc.spring.design.command;

/**
 * @Author: cc
 * @Date: 2019/12/5 16:00
 */
public class Client {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Command command = new MyCommand(receiver);
        Invoker invoker = new Invoker(command);
        invoker.invoke();
    }
}
