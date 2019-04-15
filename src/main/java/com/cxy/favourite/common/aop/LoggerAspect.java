package com.cxy.favourite.common.aop;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 面向切面监听需要监听的请求.
 */
@Aspect
@Component //@Service也可以
public class LoggerAspect {
    private static Logger logger = LoggerFactory.getLogger(LoggerAspect.class);



    @Before("within(com.cxy..*) && @annotation(logManage)")
    public void beforeMethod(JoinPoint joinPoint, LogManage logManage) {

        logger.info("before method: " +logManage.description()+" 开始");
        logger.info(joinPoint.getSignature().toString());//对象
        logger.info(parseParames(joinPoint.getArgs()));//参数
    }

//    @After("execution(* com.cxy.controller.IndexController.*(..))")
//    public void afterMethod(JoinPoint joinPoint) {
//        logger.info("after method: ");
//    }
    //方法返回之后
    @AfterReturning("within(com.cxy..*) && @annotation(logManage)")
    public void afterReturnningMethod(JoinPoint joinPoint,LogManage logManage){
        logger.info(logManage.description()+"结束");
    }


    @AfterThrowing(pointcut="within(com.cxy..*) && @annotation(logManage)",throwing = "e")
    public void afterThrowingMethid(JoinPoint joinPoint,LogManage logManage,Exception e){
            logger.error("执行"+logManage.description()+"出现异常"+e.getMessage());
    }



    public static String  parseParames(Object []params){
        if(params.length<=0||params==null){
            return "";
        }
        StringBuffer sb = new StringBuffer("传入的参数:");
        for(Object o:params){
            sb.append(ToStringBuilder.reflectionToString(o, ToStringStyle.SIMPLE_STYLE));
        }
        return ToStringBuilder.reflectionToString(sb,ToStringStyle.SIMPLE_STYLE);

    }
}
