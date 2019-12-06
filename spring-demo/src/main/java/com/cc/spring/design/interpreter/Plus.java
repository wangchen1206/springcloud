package com.cc.spring.design.interpreter;

/**
 * @Author: cc
 * @Date: 2019/12/6 14:25
 */
public class Plus implements Expression {
    @Override
    public int interpreter(Context context) {
        return context.getNum1() + context.getNum2();
    }
}
