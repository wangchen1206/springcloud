package com.cc.springcloud.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

/**
 * 配置变量 变化监听器 Demo
 *
 * @author wangchen
 * @createDate 2020/08/20
 */
@Slf4j
@Component
public class DemoEnvChangeListener implements ApplicationListener<EnvironmentChangeEvent> {

    @Autowired
    private ConfigurableEnvironment environment;

    public void onApplicationEvent(EnvironmentChangeEvent event) {
        for (String key : event.getKeys()) {
            log.info("[onApplicationEvent][key({}) 最新 value 为 {}]", key, environment.getProperty(key));
        }
    }
}
