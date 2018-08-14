package com.lgp.aop.controller;

import com.lgp.aop.aop.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @AUTHOR lgp
 * @DATE 2018/8/3 17:11
 * @DESCRIPTION
 **/
@RestController
public class AnnotationController {
    /**
     * 一定要跑一下这个方法
     * 参数自动输出
     */
    @Log(description = "first!!!")
    @RequestMapping("/first")
    public Object first(Map<String, Object> map) {
        return "first controller";
    }

    /**
     * 一定要跑一下这个方法
     * 报错自动输出
     */
    @Log(description = "doError!!!")
    @RequestMapping("/doError")
    public Object error() {
        throw new NullPointerException();
    }

    @Log(description = "second!!!")
    @RequestMapping("/second")
    public Object second() {
        return "second controller";
    }
}
