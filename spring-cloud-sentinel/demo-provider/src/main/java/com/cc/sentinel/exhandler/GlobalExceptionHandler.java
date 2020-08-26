package com.cc.sentinel.exhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/08/25
 */
@Component
@ControllerAdvice(basePackages = "com.cc.sentinel.controller")
public class GlobalExceptionHandler  {

    @ResponseBody
    @ExceptionHandler(value = BlockException.class)
    public JSONObject blockExceptionHandle(BlockException blockException){
        return new JSONObject().fluentPut("code",1024)
                .fluentPut("msg","请求被拦截，拦截类型为 "+blockException.getClass().getSimpleName());
    }
}
