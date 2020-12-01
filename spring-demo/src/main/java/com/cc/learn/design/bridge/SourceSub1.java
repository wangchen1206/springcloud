package com.cc.learn.design.bridge;

/**
 * @Author: cc
 * @Date: 2019/11/25 17:43
 */
public class SourceSub1 implements Sourceable {
    @Override
    public void method() {
        System.out.println("this is the first sub!");
    }
}
