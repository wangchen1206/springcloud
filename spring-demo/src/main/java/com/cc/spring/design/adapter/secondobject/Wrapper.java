package com.cc.spring.design.adapter.secondobject;

import com.cc.spring.design.adapter.firstclass.Source;
import com.cc.spring.design.adapter.firstclass.Targetable;

/**
 * 对象的适配器模式
 *当希望将一个对象转换成满足另一个新接口的对象时，可以创建一个Wrapper类，持有原类的一个实例，在Wrapper类的方法中，调用实例的方法就行。
 *
 * @Author: cc
 * @Date: 2019/11/25 14:30
 */
public class Wrapper implements Targetable {

    private Source source;

    public Wrapper(Source source){
        this.source = source;
    }

    @Override
    public void method1() {
        source.method1();
    }

    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }
}
