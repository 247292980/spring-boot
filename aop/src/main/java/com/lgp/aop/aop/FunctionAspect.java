package com.lgp.aop.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: a247292980
 * Date: 2017/08/14
 * <p>
 * 方法的aop
 */
@Aspect
@Component
public class FunctionAspect {
    protected org.slf4j.Logger logger = LoggerFactory.getLogger(FunctionAspect.class);

    /**
     * execution函数
     * 用于匹配方法执行的连接点，语法为：execution(方法修饰符(可选)  返回类型  方法名  参数  异常模式(可选))
     * 参数部分允许使用通配符：
     * *  匹配任意字符，但只能匹配一个元素
     * .. 匹配任意字符，可以匹配任意多个元素，表示类时，必须和*联合使用
     * +  必须跟在类名后面，如Horseman+，表示类本身和继承或扩展指定类的所有类
     * 除了execution()，Spring中还支持其他多个函数，这里列出名称和简单介绍，以方便根据需要进行更详细的查询
     * @annotation() 表示标注了指定注解的目标类方法
     * 例如 @annotation(org.springframework.transaction.annotation.Transactional) 表示标注了@Transactional的方法
     * args()通过目标类方法的参数类型指定切点 例如 args(String) 表示有且仅有一个String型参数的方法
     * @args() 通过目标类参数的对象类型是否标注了指定注解指定切点 如 @args(org.springframework.stereotype.Service) 表示有且仅有一个标注了@Service的类参数的方法
     * within()通过类名指定切点 如 with(examples.chap03.Horseman) 表示Horseman的所有方法
     * @within() 匹配标注了指定注解的类及其所有子类 如 @within(org.springframework.stereotype.Service) 给Horseman加上@Service标注，则Horseman和Elephantman 的所有方法都匹配
     * target()通过类名指定，同时包含所有子类 如 target(examples.chap03.Horseman)  且Elephantman extends Horseman，则两个类的所有方法都匹配
     * @target() 所有标注了指定注解的类 如 @target(org.springframework.stereotype.Service) 表示所有标注了@Service的类的所有方法
     * this() 大部分时候和target()相同，区别是this是在运行时生成代理类后，才判断代理类与指定的对象类型是否匹配
     * */


    /**
     * 定义有一个切入点，范围为service包下的类
     */
    @Pointcut("execution(public * com.lgp.aop.service.*.*(..))")
    public void service() {
    }

    @Around("service()")
    public void doAround(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        System.out.println("URL : " + request.getRequestURL().toString());
        System.out.println("HTTP_METHOD : " + request.getMethod());
        System.out.println("IP : " + request.getRemoteAddr());
        System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

}
