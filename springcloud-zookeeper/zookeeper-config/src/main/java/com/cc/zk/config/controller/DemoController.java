package com.cc.zk.config.controller;

import com.alibaba.fastjson.JSONObject;
import com.cc.zk.config.properties.OrderProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/08/19
 */
@RestController
@RequestMapping("/demo")
@RefreshScope //配置刷新
public class DemoController {

    @Autowired
    private OrderProperties orderProperties;

    @Value(value = "${order.pay-timeout-seconds}") // @NacosValue(value = "${order.pay-timeout-seconds}")
    private Integer payTimeoutSeconds;
    @Value(value = "${order.create-frequency-seconds}") // @NacosValue(value = "${order.create-frequency-seconds}")
    private Integer createFrequencySeconds;

    @GetMapping("/test01")
    public OrderProperties test01(){
        return orderProperties;
    }

    @GetMapping("/test02")
    public Map<String,Object> test02(){
        return new JSONObject().fluentPut("payTimeoutSeconds", payTimeoutSeconds)
                .fluentPut("createFrequencySeconds", createFrequencySeconds);
    }
}
