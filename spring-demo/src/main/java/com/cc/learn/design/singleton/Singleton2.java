package com.cc.learn.design.singleton;

/**
 * @Author: cc
 * @Date: 2019/11/22 14:15
 */
public class Singleton2 {

    private static Singleton2 instance = null;

    private Singleton2(){}

    private static synchronized void syncInit(){
        if(instance == null){
            instance = new Singleton2();
        }
    }

    public static Singleton2 getInstance(){
        if (instance == null){
            syncInit();
        }
        return instance;
    }
}
