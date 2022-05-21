package com.cc.dynamic.datasource.config;

import com.cc.dynamic.datasource.config.dynamic.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 注入多数据源
 * 注入实现AbstractRoutingDataSource的自定义数据源
 * @ClassName DynamicDataSourceConfig
 * @Desc 动态数据源配置
 * @Author DELL
 * @Date 2022/5/21
 * @Version 1.0
 **/
@Configuration
public class DynamicDataSourceConfig {

    @Bean(name = "cc")
    @ConfigurationProperties(prefix = "spring.datasource.cc.mysql")
    public DataSource ccDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "cc2")
    @ConfigurationProperties(prefix = "spring.datasource.cc2.mysql")
    public DataSource cc2DataSource(){
        return DataSourceBuilder.create().build();
    }


    /***
     注入自定义 动态数据源
     * @Description 动态切换，设置数据源
     * @Date 2022/5/21 17:13
     * @Param []
     * @return com.cc.dynamic.datasource.config.dynamic.DynamicDataSource
     **/
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dynamicDataSource(){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        //设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(ccDataSource());
        Map<Object, Object> dataSourceMap = new HashMap<>(4);
        dataSourceMap.put("cc", ccDataSource());
        dataSourceMap.put("cc2", cc2DataSource());
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        return dynamicDataSource;
    }

    /**
     * 此配置也需要配置
     * 不然会出现循环依赖的问题
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        //此处设置为了解决找不到mapper文件的问题
//        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    /**
     * 事务管理
     *
     * @return 事务管理实例
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager() throws SQLException {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
