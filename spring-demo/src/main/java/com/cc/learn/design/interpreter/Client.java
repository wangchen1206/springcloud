package com.cc.learn.design.interpreter;

/**
 * @Author: cc
 * @Date: 2019/12/6 14:26
 */
public class Client {

    public static void main(String[] args) {
        //计算9+2-8的值
        int result = new Minus().interpreter(new Context(new Plus().interpreter(new Context(9, 2)), 8));
        System.out.println(result);
    }
}
