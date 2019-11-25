package com.cc.spring.design.decorator;

/**
 * @Author: cc
 * @Date: 2019/11/25 16:15
 */
public class Client {

    public static void main(String[] args) {
        Sourceable source = new Source();
        Sourceable obj = new Decorator(source);
        obj.method();
    }
}
