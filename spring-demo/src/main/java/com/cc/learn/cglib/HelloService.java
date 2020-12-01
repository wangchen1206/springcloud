package com.cc.learn.cglib;

/**
 * @Author: cc
 * @Date: 2019/11/15 9:56
 */
public class HelloService {

    public void sayHello(){
        System.out.println("Say Hello");
    }

    public String bye(){
        System.out.println("Bye !");
        return "return Bye";
    }
}
