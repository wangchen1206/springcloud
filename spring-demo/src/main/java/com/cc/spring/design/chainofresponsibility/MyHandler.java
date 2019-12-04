package com.cc.spring.design.chainofresponsibility;

/**
 * @Author: ck
 * @Date: 2019/12/4 19:52
 */
public class MyHandler extends AbstractHandler implements Handler {

    private String name;

    public MyHandler(String name) {
        this.name = name;
    }

    @Override
    public void operator() {
        System.out.println(this.name + " deal in the first step");
        if (getHandler() != null) {
            getHandler().operator();
        }
    }
}
