#动态数据源
- --
1. 实现AbstractRoutingDataSource，实现determineCurrentLookupKey方法 
2. 各种数据源dataSource注入配置（DynamicDataSourceConfig），注入SqlSessionFactory，SqlSessionTemplate，PlatformTransactionManager
3. AOP切面注解实现动态切换（DataSourceAspect）

##学习链接
- --
https://www.jianshu.com/p/bfbfa283c7e3