package com.lgp.aop.finalVersion;

import java.lang.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: a247292980
 * Date: 2017/08/14
 * 日志注解
 * */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
     String value()  default "";
}
