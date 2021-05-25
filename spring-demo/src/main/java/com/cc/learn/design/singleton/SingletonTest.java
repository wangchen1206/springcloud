package com.cc.learn.design.singleton;

/*
 * 枚举实现单例模式
 * 能够保证序列化和反序列化过程中实例的唯一性，而且不用担心线程安全问题
 *
 * @author wangchen
 * @createDate 2021/4/12
 **/

public enum SingletonTest {

    SERVICE_A {
        @Override

        protected void hello() {

            System.out.println("hello, service A");

        }

    },

    SERVICE_B {
        @Override

        protected void hello() {

            System.out.println("hello, service B");

        }

    };

    protected abstract void hello();

    public static void main(String[] args) {
        SingletonTest.SERVICE_A.hello();
    }

}
