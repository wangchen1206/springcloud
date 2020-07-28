package com.cloud.gateway.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import java.net.URI;

/**
 * <Description>
 *
 * @author wangchen
 * @createDate 2020/07/28
 */
@RestController
@Slf4j
public class FallbackController {

    @RequestMapping("/fallback")
    public String fallback(ServerWebExchange exchange){
//        URI requestUrl = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
//        log.info("request url is: {}",requestUrl);
        Throwable executionException = exchange.getAttribute(ServerWebExchangeUtils.HYSTRIX_EXECUTION_EXCEPTION_ATTR);
        log.error("[fallback][发生异常]",executionException);

        return "服务降级..."+executionException;
    }
}
