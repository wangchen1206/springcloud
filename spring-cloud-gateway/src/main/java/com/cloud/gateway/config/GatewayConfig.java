package com.cloud.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * GatewayConfig 实现基于IP控制限流
 *
 * @author wangchen
 * @createDate 2020/07/28
 */
//@Configuration
@Slf4j
public class GatewayConfig {

    //
    /**
     * bean的名字要与配置文件里 key-resolver: "#{@ipKeyResolver}" ipKeyResolver一致
     *
     * 创建的 ipKeyResolver Bean 是通过解析请求的来源 IP 作为限流 KEY，这样我们就能实现基于 IP 的请求限流。
     * 如果说，我们想要实现基于用户的请求限流，那么我们可以创建从请求中解析用户身份的 KeyResolver Bean。
     * 也就是说，通过自定义的 KeyResolver 来实现不同粒度的请求限流
     *
     * @param []
     * @author wangchen
     * @createDate 2020/7/28
     **/
    @Bean
    public KeyResolver ipKeyResolver(){
        return new KeyResolver() {
            public Mono<String> resolve(ServerWebExchange exchange) {
                //获取请求的IP
                log.info("the ip is : {}",exchange.getRequest().getRemoteAddress().getHostName());
                return Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
            }
        };
    }
}
