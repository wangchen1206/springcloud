package com.cc.spring.design.facade;

/**
 * @Author: cc
 * @Date: 2019/11/25 16:53
 */
public class User {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.startup();
        computer.shutdown();
    }
}
