package com.cc.sentinel.client;

import com.cc.sentinel.client.fallback.DemoProviderFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/08/26
 */
@FeignClient(name = "demo-provider",url = "http://localhost:21102",fallbackFactory = DemoProviderFallbackFactory.class)
public interface DemoProviderFeignClient {

    @GetMapping("/demo/echo")
    String echo();

    @GetMapping("/demo/test")
    String test();
}
