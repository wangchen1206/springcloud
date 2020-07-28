package com.cloud.gateway.sentinel;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义实现 sentinel fallback 容错处理类
 *
 * @author wangchen
 * @createDate 2020/07/28
 */
@Component
public class CustomerBlockRequestHandler implements BlockRequestHandler {

    private static final String DEFAULT_BLOCK_MSG_PREFIX = "HAHAHA ~ Blocked by Sentinel: ";


    @Override
    public Mono<ServerResponse> handleRequest(ServerWebExchange exchange, Throwable ex) {
        return ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS)//状态码
                .contentType(MediaType.TEXT_PLAIN)//内容响应类型为 text/plain 纯文本
                .bodyValue(DEFAULT_BLOCK_MSG_PREFIX + ex.getClass().getSimpleName());
    }
}
