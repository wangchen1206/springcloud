package com.cc.learn.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * @Author: cc
 * @Date: 2019/11/15 10:08
 */
public class Client {
    public static void main(String[] args) {
        //代理类class文件存入本地，方便我们反编译查看源码
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"C:\\Git_Java\\spring-demo");
        //通过cglib动态代理获取代理对象
        Enhancer enhancer = new Enhancer();
        //设置enhancer对象的父类
        enhancer.setSuperclass(HelloService.class);
        //设置enhancer对象的回掉函数
        enhancer.setCallback(new HelloMethodInterceptor());
        //创建代理对象
        HelloService helloService = (HelloService) enhancer.create();
        //通过代理对象调用目标方法
        helloService.sayHello();
        String bye = helloService.bye();
        System.out.println(bye);
    }
}
