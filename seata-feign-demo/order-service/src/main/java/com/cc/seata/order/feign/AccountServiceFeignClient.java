package com.cc.seata.order.feign;

import com.cc.seata.order.dto.AccountReduceBalanceDTO;
import com.cc.seata.order.feign.fallback.AccountServiceFeignClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: ck
 * @Date: 2020/7/12 21:39
 */
//name是注册中心中服务的名字
@FeignClient(name = "account-service",fallbackFactory = AccountServiceFeignClientFallbackFactory.class)
public interface AccountServiceFeignClient {

    @PostMapping("/account/reduce-balance")
    void reduceBalance(@RequestBody AccountReduceBalanceDTO accountReduceBalanceDTO);
}
