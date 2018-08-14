package com.lgp.aop.aop;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: a247292980
 * Date: 2017/08/14
 * 日志管理
 */
@Aspect
@Service
public class LoggerAspect {
    protected org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("within(com.lgp.aop..*) && @annotation(log)")
    public void addBeforeLogger(JoinPoint joinPoint, Log log) {
        logger.info("执行 " + log.description() + " 开始");
        logger.info("地址 " + joinPoint.getSignature().toString());
        logger.info("参数 " + this.parseParames(joinPoint.getArgs()));
    }

    @AfterReturning("within(com.lgp.aop..*) && @annotation(log)")
    public void addAfterReturningLogger(JoinPoint joinPoint, Log log) {
        logger.info("执行 " + log.description() + " 结束");
    }

    @AfterThrowing(pointcut = "within(com.lgp.aop..*) && @annotation(log)", throwing = "ex")
    public void addAfterThrowingLogger(JoinPoint joinPoint, Log log, Exception ex) {
        logger.error("执行 " + log.description() + " 异常,error={}", ex.getMessage(), ex);
    }

    private String parseParames(Object[] parames) {
        if (null == parames || parames.length <= 0) {
            return "";
        }
        StringBuffer param = new StringBuffer("传入参数[");
        for (Object obj : parames) {
            param.append("{").append(ToStringBuilder.reflectionToString(obj)).append("}  ");
        }
        param.append("]");
        return param.toString();
    }
}
