package com.cc.spring.design.chainofresponsibility;

/**
 * @Author: ck
 * @Date: 2019/12/4 19:58
 */
public class Client {
    public static void main(String[] args) {
        MyHandler h1 = new MyHandler("h1");
        MyHandler2 h2 = new MyHandler2("h2");
        MyHandler3 h3 = new MyHandler3("h3");
        h1.setHandler(h2);
        h2.setHandler(h3);

        h1.operator();
    }
}
