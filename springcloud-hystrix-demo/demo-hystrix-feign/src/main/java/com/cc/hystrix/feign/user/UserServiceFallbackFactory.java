package com.cc.hystrix.feign.user;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

/**
 * UserServiceClient 服务降级回调类
 *
 * @author wangchen
 * @createDate 2020/08/24
 */
@Component
@Slf4j
public class UserServiceFallbackFactory implements FallbackFactory<UserServiceFeignClient> {
    public UserServiceFeignClient create(final Throwable cause) {
        return new UserServiceFeignClient() {
            public String getUser(String name) {
                log.info("[getUserFallback][name({}) exception({})]", name, ExceptionUtils.getRootCauseMessage(cause));
                return "mock: User: "+name;
            }
        };
    }
}
