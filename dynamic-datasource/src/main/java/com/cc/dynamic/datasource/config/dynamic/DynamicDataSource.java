package com.cc.dynamic.datasource.config.dynamic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源设置
 * @ClassName DynamicDataSource
 * @Desc 动态数据源设置
 * @Author DELL
 * @Date 2022/5/21
 * @Version 1.0
 **/
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 保证多线程的数据正确
     **/
    private static final ThreadLocal<String> DATA_SOURCE_KEY = new ThreadLocal<>();

    public static void changeDataSourceKey(String key){
        DATA_SOURCE_KEY.set(key);
    }

    /**
     * 防止内存溢出
     * @Author wangchen
     * @Description 防止内存溢出
     * @Date 2022/5/21 16:56
     * @Param []
     * @return void
     **/
    public static void cleanDataSource(){
        DATA_SOURCE_KEY.remove();
    }

    
    /***
     * 这里是决定数据源的关键
     * 数据源是通过Map结构存的
     * 这里返回一个key值 再去获取真正的数据源连接
     * @Author wangchen
     * @Description org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineTargetDataSource() 调用，选择数据源
     * @Date 2022/5/21 16:57
     * @Param []
     * @return java.lang.Object
     **/
    @Override
    protected Object determineCurrentLookupKey() {
        String s = DATA_SOURCE_KEY.get();
        log.info("current datasource key : "+s);
        return s;
    }


}
