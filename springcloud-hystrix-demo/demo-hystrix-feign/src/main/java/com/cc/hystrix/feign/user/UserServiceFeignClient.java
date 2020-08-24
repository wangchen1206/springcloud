package com.cc.hystrix.feign.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/08/24
 */
@FeignClient(name = "demo-provider",fallbackFactory = UserServiceFallbackFactory.class)
public interface UserServiceFeignClient {

    @GetMapping("/user/get")
    String getUser(@RequestParam("name") String name);
}
