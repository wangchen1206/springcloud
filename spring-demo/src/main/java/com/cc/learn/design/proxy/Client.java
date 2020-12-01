package com.cc.learn.design.proxy;

/**
 * @Author: cc
 * @Date: 2019/11/25 16:38
 */
public class Client {
    public static void main(String[] args) {
        Sourceable source = new Proxy();
        source.method();
    }
}
