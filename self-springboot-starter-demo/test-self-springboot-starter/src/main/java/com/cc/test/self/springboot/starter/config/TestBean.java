package com.cc.test.self.springboot.starter.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName TestBean
 * @Desc TODO
 * @Author DELL
 * @Date 2022/5/2
 * @Version 1.0
 **/
@Component
public class TestBean implements ApplicationContextAware {

    private Integer a;

    private String b;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }



}
