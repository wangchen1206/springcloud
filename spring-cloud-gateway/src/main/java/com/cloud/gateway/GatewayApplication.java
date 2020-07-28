package com.cloud.gateway;

import com.alibaba.cloud.sentinel.gateway.ConfigConstants;
import com.alibaba.csp.sentinel.config.SentinelConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description 
 *
 * @param
 * @author wangchen 
 * @createDate 2020/7/24
 **/
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        System.setProperty(SentinelConfig.APP_TYPE, ConfigConstants.APP_TYPE_SCG_GATEWAY);//【重点】 应用类型设置为Spring Cloud Gateway
        SpringApplication.run(GatewayApplication.class, args);
    }

}
