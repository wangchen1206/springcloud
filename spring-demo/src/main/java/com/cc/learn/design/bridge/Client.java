package com.cc.learn.design.bridge;

/**
 * @Author: cc
 * @Date: 2019/11/25 17:48
 */
public class Client {
    public static void main(String[] args) {
        Bridge bridge = new MyBridge();

        //调用第一个对象
        Sourceable sub1 = new SourceSub1();
        bridge.setSource(sub1);
        bridge.method();

        //调用第二个对象
        Sourceable sub2 = new SourceSub2();
        bridge.setSource(sub2);
        bridge.method();
    }
}
