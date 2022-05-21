package com.cc.dynamic.datasource.config.aspect;

import com.cc.dynamic.datasource.config.annotation.DataSource;
import com.cc.dynamic.datasource.config.dynamic.DynamicDataSource;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


/**
 * 数据源动态切换切面
 * @ClassName DataSourceAspect
 * @Desc 数据源动态切换切面
 * @Author DELL
 * @Date 2022/5/21
 * @Version 1.0
 **/
@Aspect
@Component
public class DataSourceAspect {

    @Before("@annotation(dataSource)")
    public void beforeSwitchDataSource(DataSource dataSource){
        DynamicDataSource.changeDataSourceKey(dataSource.value());
    }

    @After("@annotation(dataSource)")
    public void afterSwitchDataSource(DataSource dataSource){
        DynamicDataSource.cleanDataSource();
    }
}
