package com.cc.spring.aspect;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * @Author: cc
 * @Date: 2019/11/12 15:07
 */
@Component
@Aspect
public class ControllerAop {

    private Logger logger = LoggerFactory.getLogger(ControllerAop.class);

    /**
     * 定义切点
     */
    @Pointcut("execution(public * com.cc.spring.controller.DemoController.*(..))")
    public void controllerLog() {
    }

    /**
     * 前置通知
     *
     * @param joinPoint
     */
    @Before("controllerLog()")
    public void doBefore(JoinPoint joinPoint) {
        //接收到请求，收到请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //记录请求内容
        logger.info("the request url is :" + request.getRequestURL());
        logger.info("the http method is :" + request.getMethod());
        logger.info("the request ip is :" + request.getRemoteAddr());
        logger.info("the class_method is :" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("the args is :" + Arrays.toString(joinPoint.getArgs()));

    }

    /**
     * 定义后置通知
     *
     * @param joinPoint
     */
    @AfterReturning(value = "controllerLog()",returning = "ret")
    public void doAfter(JoinPoint joinPoint, Map ret) {
        try {
            //处理完请求，返回内容
            logger.info("the request is over");
            logger.info("the class_method is :" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            logger.info("the response is "+ret.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
