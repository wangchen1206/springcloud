package com.cc.spring.design.visitor;

/**
 * @Author: cc
 * @Date: 2019/12/6 13:31
 */
public interface Subject {
    public void accept(Visitor visitor);
    public String getSubject();
}
