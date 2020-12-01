package com.cc.learn.design.visitor;

/**
 * @Author: cc
 * @Date: 2019/12/6 13:36
 */
public class Client {
    public static void main(String[] args) {
        Subject subject = new MySubject();
        Visitor visitor = new MyVisitor();
        subject.accept(visitor);

    }
}
