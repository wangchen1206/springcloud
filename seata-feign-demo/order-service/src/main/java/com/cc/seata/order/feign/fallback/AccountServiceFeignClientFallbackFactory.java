package com.cc.seata.order.feign.fallback;

import com.cc.seata.order.feign.AccountServiceFeignClient;
import com.cc.seata.order.dto.AccountReduceBalanceDTO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <Description>
 *
 * @author wangchen
 * @createDate 2020/07/22
 */
@Component
@Slf4j
public class AccountServiceFeignClientFallbackFactory implements FallbackFactory<AccountServiceFeignClient> {
    @Override
    public AccountServiceFeignClient create(Throwable throwable) {
        return new AccountServiceFeignClient() {
            @Override
            public void reduceBalance(AccountReduceBalanceDTO accountReduceBalanceDTO) {
                log.error("系统发生异常！，进行服务降级");
            }
        };
    }
}
