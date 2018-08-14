package com.lgp.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @AUTHOR lgp
 * @DATE 2018/8/3 17:12
 * @DESCRIPTION
 **/
@Aspect
@Component
public class AnnotationAspect {
    protected org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @Aspect 作用是把当前类标识为一个切面供容器读取
     * @Before 标识一个前置增强方法，相当于BeforeAdvice的功能
     * @AfterReturning 后置增强，相当于AfterReturningAdvice，方法退出时执行
     * @AfterThrowing 异常抛出增强，相当于ThrowsAdvice
     * @After final增强，不管是抛出异常或者正常退出都会执行
     * @Around 环绕增强，相当于MethodInterceptor
     *
     * 除了@Around外，每个方法里都可以加或者不加参数JoinPoint，
     * 如果有用JoinPoint的地方就加，不加也可以，JoinPoint里包含了类名、被切面的方法名，参数等属性，可供读取使用。
     * @Around参数必须为ProceedingJoinPoint pjp.proceed相应于执行被切面的方法。
     * @AfterReturning方法里， 可以加returning = “XXX”，XXX即为在controller里方法的返回值。
     * @AfterThrowing方法里， 可以加throwing = "XXX"，供读取异常信息，throwing = "ex"
     */


    @Pointcut(value = "@annotation(com.lgp.aop.aop.Log)")
    public void log() {
    }

    @Before("log()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        System.out.println("annotation before");
    }

    @Around("@annotation(log)")
    public Object around(ProceedingJoinPoint pjp, Log log) {
        //获取注解里的值
        System.out.println("annotation around:" + log.description());
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
