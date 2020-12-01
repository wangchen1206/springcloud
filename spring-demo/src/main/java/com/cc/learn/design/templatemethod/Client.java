package com.cc.learn.design.templatemethod;

/**
 * @Author: cc
 * @Date: 2019/11/26 15:09
 */
public class Client {
    public static void main(String[] args) {
        AbstractCalculator abstractCalculator = new Plus();
        String exp = "2+8";
        int calculate = abstractCalculator.calculate(exp, "\\+");
        System.out.println(calculate);

    }
}
