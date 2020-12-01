package com.cc.learn.design.bridge;

/**
 * @Author: cc
 * @Date: 2019/11/25 17:44
 */
public class SourceSub2 implements Sourceable {
    @Override
    public void method() {
        System.out.println("this is the second sub!");
    }
}
