package com.cc.springcloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.cc.springcloud.properties.ExtensionPropertiesFirst;
import com.cc.springcloud.properties.ExtensionPropertiesSecond;
import com.cc.springcloud.properties.OrderProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/demo")
@RefreshScope  //刷新 @Value 注解的配置
public class DemoController {

    @Autowired
    private OrderProperties orderProperties;

    @Autowired
    private ExtensionPropertiesFirst propertiesFirst;

    @Autowired
    private ExtensionPropertiesSecond propertiesSecond;

    /**
     * 测试 @ConfigurationProperties 注解的配置属性类
     */
    @GetMapping("/test01")
    public OrderProperties test01() {
        return orderProperties;
    }

    @Value(value = "${order.pay-timeout-seconds}") // @NacosValue(value = "${order.pay-timeout-seconds}")
    private Integer payTimeoutSeconds;
    @Value(value = "${order.create-frequency-seconds}") // @NacosValue(value = "${order.create-frequency-seconds}")
    private Integer createFrequencySeconds;

    @Value(value = "${num}")
    private Integer num;

    /**
     * 测试 @Value 注解的属性
     */
    @GetMapping("/test02")
    public Map<String, Object> test02() {
        return new JSONObject().fluentPut("payTimeoutSeconds", payTimeoutSeconds)
                .fluentPut("createFrequencySeconds", createFrequencySeconds);
    }

    @GetMapping("/test03")
    public ExtensionPropertiesFirst test03() {
        return propertiesFirst;
    }

    @GetMapping("/test04")
    public ExtensionPropertiesSecond test04() {
        return propertiesSecond;
    }

}