package com.cc.spring.design.singleton;

/**
 * @Author: cc
 * @Date: 2019/11/22 14:00
 */
public class Singleton {

    //私有构造函数,防止被实例化
    private Singleton(){}

    //使用内部类来维护实例
     private static class SingletonFactory{
        private static Singleton instance = new Singleton();
    }

    //获取实例
    public static Singleton getInstance(){
        return SingletonFactory.instance;
    }

    //如果该对象被用于序列化，可以保证该对象在序列化前后保持一致
    public Object readResolve(){
        return getInstance();
    }
}
