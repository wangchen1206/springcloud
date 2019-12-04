package com.cc.spring.design.chainofresponsibility;

/**
 * @Author: ck
 * @Date: 2019/12/4 20:39
 */
public class MyHandler3 extends AbstractHandler implements Handler {

    private String name;

    public MyHandler3(String name) {
        this.name = name;
    }

    @Override
    public void operator() {
        System.out.println(this.name + "deal in the third step");
        if (getHandler() != null){
            getHandler().operator();
        }else {
            System.out.println("the chain of responsibility is over!");
        }
    }
}
