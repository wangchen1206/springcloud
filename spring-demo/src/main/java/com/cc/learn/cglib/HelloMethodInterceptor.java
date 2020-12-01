package com.cc.learn.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: cc
 * @Date: 2019/11/15 10:04
 */
public class HelloMethodInterceptor implements MethodInterceptor {

    /**
     * @param o           cglib生成被代理类的子类对象
     * @param method      被代理的对象的方法
     * @param objects     方法入参
     * @param methodProxy 代理方法
     * @return 代理方法返回值
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before " + method.getName());
        Object o1 = methodProxy.invokeSuper(o, objects);
        System.out.println("After " + method.getName());
        if (o1 != null) {
            System.out.println("Proxy Return : " + o1.toString());
        }
        return o1;
    }
}
