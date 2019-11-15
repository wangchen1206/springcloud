package com.cc.spring.jdkproxy;

/**
 * @Author: cc
 * @Date: 2019/11/14 15:01
 */
public class Client {
    public static void main(String[] args) {
        //保存生成的代理类的字节码文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles",true);

        //jdk动态代理测试
        Subject subject = new JDKDynamicProxy(new RealSubject()).getProxy();
        subject.doSomething();
    }
}
