package com.cc.spring.design.visitor;

/**
 * @Author: cc
 * @Date: 2019/12/6 13:33
 */
public class MyVisitor implements Visitor {
    @Override
    public void visit(Subject subject) {
        System.out.println("visit the subject: "+subject.getSubject());
    }
}
