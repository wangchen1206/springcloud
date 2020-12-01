package com.cc.learn.design.adapter.firstclass;

/**
 * @Author: cc
 * @Date: 2019/11/25 14:26
 */
public class Client {

    public static void main(String[] args) {
        Targetable targetable = new Adapter();
        targetable.method1();
        targetable.method2();
    }
}
