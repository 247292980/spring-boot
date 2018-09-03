package com.lgp.droolsdrt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口对象属性校验空值标记
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {

	/**
	 * 是否允许为空。true：是；false：否(默认)
	 */
	boolean value() default false;
	
	/**
	 * 错误信息
	 */
	String text() default "Field Is Empty Exception!";
}
