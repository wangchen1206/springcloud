package com.cc.learn.design.visitor;

/**
 * @Author: cc
 * @Date: 2019/12/6 13:34
 */
public class MySubject implements Subject {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getSubject() {
        return "love";
    }
}
