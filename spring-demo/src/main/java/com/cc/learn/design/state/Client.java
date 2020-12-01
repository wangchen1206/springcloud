package com.cc.learn.design.state;

/**
 * @Author: cc
 * @Date: 2019/12/6 10:40
 */
public class Client {
    public static void main(String[] args) {
        State state = new State();
        Context context = new Context(state);

        //一个状态
        state.setValue("state1");
        context.method();
        //切换状态
        state.setValue("state2");
        context.method();
    }
}
