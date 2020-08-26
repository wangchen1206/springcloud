package com.cc.sentinel.client.fallback;

import com.cc.sentinel.client.DemoProviderFeignClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/08/26
 */
@Component
public class DemoProviderFallbackFactory implements FallbackFactory<DemoProviderFeignClient> {
    @Override
    public DemoProviderFeignClient create(Throwable cause) {
        return new DemoProviderFeignClient() {
            @Override
            public String echo() {
                return "fallback: echo: "+cause.getClass().getSimpleName();
            }

            @Override
            public String test() {
                return "fallback: test: "+cause.getClass().getSimpleName();
            }
        };
    }
}
