package com.cc.spring.design.strategy;

/**
 * @Author: cc
 * @Date: 2019/11/26 14:32
 */
public class Client {
    public static void main(String[] args) {
        String exp = "2+8";
        ICalculator iCalculator = new Plus();
        int result = iCalculator.calculate(exp);
        System.out.println(result);
    }
}
