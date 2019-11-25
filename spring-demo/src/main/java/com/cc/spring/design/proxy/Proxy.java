package com.cc.spring.design.proxy;

/**
 * 代理模式
 * 就是采用一个代理类调用原有的方法，且对产生的结果进行控制
 * 使用代理模式，可以将功能划分的更加清晰，有助于后期维护
 *
 * @Author: cc
 * @Date: 2019/11/25 16:34
 */
public class Proxy implements Sourceable {

    private Sourceable source;

    public Proxy() {
        super();
        this.source = new Source();
    }

    @Override
    public void method() {
        before();
        source.method();
        after();
    }

    private void after() {
        System.out.println("after proxy");
    }

    private void before() {
        System.out.println("before proxy");
    }
}
