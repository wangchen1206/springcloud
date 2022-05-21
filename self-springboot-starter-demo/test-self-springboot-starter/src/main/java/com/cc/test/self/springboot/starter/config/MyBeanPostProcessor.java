package com.cc.test.self.springboot.starter.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @ClassName MyBeanPostProcessor
 * @Desc TODO
 * @Author DELL
 * @Date 2022/5/2
 * @Version 1.0
 **/
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("before");
        if (beanName.equals("testService")){
            System.out.println(111);
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("after");
        if (beanName.equals("testService")){
            System.out.println(222);
        }
        return bean;
    }
}
