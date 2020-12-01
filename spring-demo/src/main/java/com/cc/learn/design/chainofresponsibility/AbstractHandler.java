package com.cc.learn.design.chainofresponsibility;

/**
 * @Author: ck
 * @Date: 2019/12/4 19:49
 */
public abstract class AbstractHandler {


    private Handler handler;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }


}
