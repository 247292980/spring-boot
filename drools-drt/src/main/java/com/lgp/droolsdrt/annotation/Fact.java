package com.lgp.droolsdrt.annotation;

import com.lgp.droolsdrt.fact.ActivityEvent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表示该类需要被解析
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Fact {

    ActivityEvent value();

    String prefix() default "";
}
