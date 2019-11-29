package com.cc.spring.design.strategy;

/**
 * @Author: cc
 * @Date: 2019/11/26 14:20
 */
public class Plus extends AbstractCalculator implements ICalculator {
    @Override
    public int calculate(String exp) {
        int[] arrayInt = split(exp,"\\+");
        return arrayInt[0] + arrayInt[1];
    }
}
