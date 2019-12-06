package com.cc.spring.design.interpreter;

/**
 * @Author: cc
 * @Date: 2019/12/6 14:26
 */
public class Minus implements Expression {
    @Override
    public int interpreter(Context context) {
        return context.getNum1() - context.getNum2();
    }
}
