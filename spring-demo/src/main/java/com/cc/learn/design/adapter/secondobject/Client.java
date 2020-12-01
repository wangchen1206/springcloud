package com.cc.learn.design.adapter.secondobject;

import com.cc.learn.design.adapter.firstclass.Source;
import com.cc.learn.design.adapter.firstclass.Targetable;

/**
 * @Author: cc
 * @Date: 2019/11/25 14:37
 */
public class Client {
    public static void main(String[] args) {
        Source source = new Source();
        Targetable targetable = new Wrapper(source);
        targetable.method2();
        targetable.method1();
    }
}
