package com.cc.learn.design.adapter.thirdinterface;

/**
 * 接口的适配器模式
 * 当不希望实现一个接口中所有的方法时，可以创建一个抽象类Wrapper，实现所有方法，我们写别的类的时候，继承抽象类即可。
 *
 * @Author: cc
 * @Date: 2019/11/25 14:48
 */
public abstract class Wrapper implements Sourceable{
    @Override
    public void method1() {

    }

    @Override
    public void method2() {

    }
}
