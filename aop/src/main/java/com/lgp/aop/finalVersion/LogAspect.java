package com.lgp.aop.finalVersion;


import com.alibaba.fastjson.JSON;
import com.lgp.aop.util.StringUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by IntelliJ IDEA.
 * User: a247292980
 * Date: 2017/08/14
 * 日志管理
 */
@Aspect
@Service
public class LogAspect {
    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);
    /**
     * 获取登录人userId
     */
    private static final String USERIDKEY = "tuandaiw";

    @Pointcut("@annotation(com.lgp.aop.finalVersion.Log)")
    public void logPointcut() {
    }

    @Before("logPointcut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 目标类
        String className = joinPoint.getTarget().getClass().getSimpleName();
        // 目标方法
        String methodName = signature.getName();
        // 目标参数名字
        String[] parmNames = signature.getParameterNames();
        // 目标参数
        Object[] parms = joinPoint.getArgs();
        // 目标标签
        Log logAnnotation = signature.getMethod().getAnnotation(Log.class);
        buildBeforeLog(className, methodName, parmNames, parms, logAnnotation);
    }

    @AfterReturning(pointcut = "logPointcut()", returning = "obj")
    public void addAfterReturningLogger(JoinPoint joinPoint, Object obj) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 目标类
        String className = joinPoint.getTarget().getClass().getSimpleName();
        // 目标方法
        String methodName = signature.getName();
        // 目标标签
        Log logAnnotation = signature.getMethod().getAnnotation(Log.class);

        logger.info(className + "." + methodName + "." + logAnnotation.value() + "-结束 return={}", JSON.toJSON(obj));
    }

    @AfterThrowing(pointcut = "logPointcut()", throwing = "ex")
    public void addAfterThrowingLogger(JoinPoint joinPoint, Exception ex) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 目标类
        String className = joinPoint.getTarget().getClass().getSimpleName();
        // 目标方法
        String methodName = signature.getName();
        // 目标标签
        Log logAnnotation = signature.getMethod().getAnnotation(Log.class);
        logger.error(className + "." + methodName + "." + logAnnotation.value() + "-异常 e={}", ex.getMessage(), ex);
    }

    private void buildBeforeLog(String className, String methodName, String[] parmNames, Object[] parms, Log logAnnotation) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(className);
        stringBuilder.append(".");
        stringBuilder.append(methodName);
        stringBuilder.append(".");
        stringBuilder.append(logAnnotation.value());
        stringBuilder.append("-开始 ");

        String userId = getUserId();
        if (StringUtil.isBlank(userId)) {
            stringBuilder.append("(用户未登录){");
        } else {
            stringBuilder.append("{userId=").append(userId);
        }

        if (parmNames.length != 0) {
            for (int i = 0; i < parmNames.length; i++) {
                stringBuilder.append(",");
                stringBuilder.append(parmNames[i]);
                stringBuilder.append("=");

//                System.out.println(parmNames[i]);
                if ("response".equals(parmNames[i])) {
                    stringBuilder.append(parms[i]);
                } else if ("request".equals(parmNames[i])) {
                    stringBuilder.append(parms[i]);
                } else {
                    stringBuilder.append(JSON.toJSON(parms[i]));
                }
            }
        }
        stringBuilder.append("}");
        logger.info(stringBuilder.toString());

    }

    public String getUserId() {
        String cookvalue = getCookie("userId");
        String userId = "";
        if (!StringUtil.isBlank(cookvalue) && cookvalue.split("\\|").length > 1) {
            userId = cookvalue.split("\\|")[0];
        }
        return userId;
    }

    public static String getCookie(String key) {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attrs.getRequest();
        Cookie cookie = WebUtils.getCookie(request, key);
        if (cookie != null) {
            String ckValue = cookie.getValue();
            try {
                ckValue = URLDecoder.decode(ckValue, "UTF-8");
            } catch (UnsupportedEncodingException e1) {
                //ignore
            }
            return ckValue;
        }
        return null;
    }
}
