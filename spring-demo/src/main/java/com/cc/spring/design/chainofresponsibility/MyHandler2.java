package com.cc.spring.design.chainofresponsibility;

/**
 * @Author: ck
 * @Date: 2019/12/4 20:08
 */
public class MyHandler2 extends AbstractHandler implements Handler {

    private String name;

    public MyHandler2(String name) {
        this.name = name;
    }

    @Override
    public void operator() {
        System.out.println(this.name + "deal in second the step");
        if(getHandler() != null){
            getHandler().operator();
        }
    }
}
