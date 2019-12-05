package com.cc.spring.design.memento;

/**
 * 备忘录类
 *
 * @Author: cc
 * @Date: 2019/12/5 16:42
 */
public class Memento {

    private String value;

    public Memento(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
