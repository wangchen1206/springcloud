package com.cc.spring.design.state;

/**
 * 状态模式
 * 不同的状态具有不同的行为。当对象的状态改变时，同时改变其行为
 *
 * 状态类的核心类
 *
 * @Author: cc
 * @Date: 2019/12/6 9:49
 */
public class State {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void method1(){
        System.out.println("execute the first opt!");
    }

    public void method2(){
        System.out.println("execute the second opt!");
    }
}
