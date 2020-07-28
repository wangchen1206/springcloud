package com.cloud.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * GateWay Auth Filter  模拟认证流程
 *
 * @author wangchen
 * @createDate 2020/07/27
 */
//使用时，记得打开下边注释
//@Component
public class AuthGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthGatewayFilterFactory.Config> {

    //保证父类能够创建Config对象
    public AuthGatewayFilterFactory(){
        super(AuthGatewayFilterFactory.Config.class);
    }


    @Override
    public GatewayFilter apply(final Config config) {
        //<1> token 和 userId的映射
        final Map<String,Integer> tokenMap = new HashMap<String, Integer>();
        tokenMap.put("cc",1);

        //创建GatewayFilter 对象
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                //<2> 获得token
                ServerHttpRequest request = exchange.getRequest();
                HttpHeaders headers = request.getHeaders();
                String token = headers.getFirst(config.getTokenHeaderName());

                //<3> 如果没有token，则不进行认证。因为可能是无需认证的API接口。
                if (!StringUtils.hasText(token)){
                    return chain.filter(exchange);
                }

                //<4> 进行认证
                ServerHttpResponse response = exchange.getResponse();
                Integer userId = tokenMap.get(token);

                //<5> 通过token获取不到userId,说明认证不通过。
                if (userId == null){
                    //响应401状态码
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    //响应提示
                    DataBuffer buffer = exchange.getResponse().bufferFactory().wrap("认证不通过".getBytes());
                    return response.writeWith(Flux.just(buffer));
                }

                //<6> 认证通过，将userId添加到Header中
                request = request.mutate().header(config.getUserIdHeaderName(),String.valueOf(userId)).build();
                return chain.filter(exchange.mutate().request(request).build());
            }
        };
    }



    public static class Config{
        private static final String DEFAULT_TOKEN_HEADER_NAME = "token";
        private static final String DEFAULT_HEADER_NAME = "user-id";

        private String tokenHeaderName = DEFAULT_TOKEN_HEADER_NAME;
        private String userIdHeaderName = DEFAULT_HEADER_NAME;

        public String getTokenHeaderName() {
            return tokenHeaderName;
        }

        public Config setTokenHeaderName(String tokenHeaderName) {
            this.tokenHeaderName = tokenHeaderName;
            return this;
        }

        public String getUserIdHeaderName() {
            return userIdHeaderName;
        }

        public Config setUserIdHeaderName(String userIdHeaderName) {
            this.userIdHeaderName = userIdHeaderName;
            return this;
        }
    }
}
