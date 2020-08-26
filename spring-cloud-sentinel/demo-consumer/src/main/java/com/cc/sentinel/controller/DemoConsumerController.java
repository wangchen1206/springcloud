package com.cc.sentinel.controller;

import com.cc.sentinel.client.DemoProviderFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/08/26
 */
@RestController
@RequestMapping("/consumer")
public class DemoConsumerController {

    @Autowired
    private DemoProviderFeignClient demoProviderFeignClient;

    @GetMapping("/echo")
    public String echo(){
        return demoProviderFeignClient.echo();
    }
}
