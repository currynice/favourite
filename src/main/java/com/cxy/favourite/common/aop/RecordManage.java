package com.cxy.favourite.common.aop;

import java.lang.annotation.*;

/**
 * 记录 前台用户操作记录 的注解 方法Method级别
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RecordManage {
    String value() default "";
}
