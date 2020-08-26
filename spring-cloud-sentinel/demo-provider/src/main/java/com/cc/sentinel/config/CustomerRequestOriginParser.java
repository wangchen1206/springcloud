package com.cc.sentinel.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求源解析器 使用 Sentinel 黑白名单控制的功能，所以需要获得请求的调用来。RequestOriginParser 暂时没有提供默认的实现
 *
 * @author wangchen
 * @createDate 2020/08/26
 */
@Component
public class CustomerRequestOriginParser implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest request) {
        // <X> 从Header中获取到请求源
        String origin = request.getHeader("s-user");
        // <Y> 如果为空，给一个默认的
        if (StringUtils.isEmpty(origin)) {
            origin = "default";
        }
        return origin;
    }
}
