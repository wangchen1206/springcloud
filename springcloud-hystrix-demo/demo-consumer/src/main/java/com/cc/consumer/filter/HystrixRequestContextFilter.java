package com.cc.consumer.filter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 初始化同一个请求上下文 过滤器
 *
 * @author wangchen
 * @createDate 2020/08/21
 */
@Component
@WebFilter(urlPatterns = "/")
public class HystrixRequestContextFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        //初始化HystrixRequestContext
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        //继续过滤器
        try {
            chain.doFilter(request,response);
        } finally {
            //销毁HystrixRequestContext
            context.close();
        }
    }
}
