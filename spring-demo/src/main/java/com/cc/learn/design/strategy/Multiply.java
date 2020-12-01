package com.cc.learn.design.strategy;

/**
 * @Author: cc
 * @Date: 2019/11/26 14:25
 */
public class Multiply extends AbstractCalculator implements ICalculator {
    @Override
    public int calculate(String exp) {
        int[] arrayInt = split(exp,"\\*");
        return arrayInt[0] * arrayInt[1];
    }
}
