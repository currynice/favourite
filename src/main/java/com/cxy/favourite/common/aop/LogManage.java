package com.cxy.favourite.common.aop;



import java.lang.annotation.*;


/**
 * @Description: 日志注解
 *
 *description :描述希望监控的事件 :
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogManage {

     String description();
}
