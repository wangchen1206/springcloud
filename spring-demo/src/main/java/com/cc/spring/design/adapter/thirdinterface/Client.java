package com.cc.spring.design.adapter.thirdinterface;

/**
 * @Author: cc
 * @Date: 2019/11/25 14:53
 */
public class Client {
    public static void main(String[] args) {
        Sourceable sub1 = new SourceSub1();
        Sourceable sub2 = new SourceSub2();
        sub1.method1();
        sub1.method2();
        sub2.method1();
        sub2.method2();
    }
}
