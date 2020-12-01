package com.cc.learn.design.state;

/**
 * 状态模式切换类
 *
 * @Author: cc
 * @Date: 2019/12/6 10:36
 */
public class Context {
    private State state;

    public Context(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void method(){
        if (this.state.getValue().equals("state1")){
            this.state.method1();
        }else if (this.state.getValue().equals("state2")){
            this.state.method2();
        }
    }
}
