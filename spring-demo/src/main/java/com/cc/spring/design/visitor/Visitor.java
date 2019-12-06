package com.cc.spring.design.visitor;

/**
 * 访问者模式
 * 访问者模式就是一种分离对象数据结构与行为的方法，通过这种分离，可达到为一个被访问者动态添加新的操作而无需做其它的修改的效果
 *
 * @Author: cc
 * @Date: 2019/12/6 13:32
 */
public interface Visitor {
    public void visit(Subject subject);
}
