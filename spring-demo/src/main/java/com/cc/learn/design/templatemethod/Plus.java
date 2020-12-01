package com.cc.learn.design.templatemethod;


/**
 * @Author: cc
 * @Date: 2019/11/26 14:20
 */
public class Plus extends AbstractCalculator{

    @Override
    public int calculate(int num1, int num2) {
        return num1 + num2;
    }
}
