package com.cc.consumer.config;

import ribbon.DefaultRibbonClientConfiguration;
import ribbon.DemoProviderRibbonClientConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;

/**
 * Ribbon 配置
 *
 * @author wangchen
 * @createDate 2020/08/18
 */
@Configuration
@RibbonClients(
        value = {
                @RibbonClient(name = "demo-provider",configuration = DemoProviderRibbonClientConfiguration.class) //客户端级别负载均衡策略配置
        },
        defaultConfiguration = DefaultRibbonClientConfiguration.class  //全局负载均衡策略配置
)
public class RibbonConfiguration {
}
