package com.cc.dynamic.datasource.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @Author wangchen
 * @Description 动态数据源注解
 * @Date 2022/5/21 17:19
 * @Param
 * @return
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
    String value();
}
