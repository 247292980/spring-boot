package com.lgp.aop.controller;

import com.lgp.aop.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @AUTHOR lgp
 * @DATE 2018/7/31 15:25
 * @DESCRIPTION
 **/
@RequestMapping("")
@Controller
public class HiController {
    @Autowired
    private HiService hiService;

    /**
     * 一定要跑一下这个方法
     * 拦截service，不拦截conreoller
     */
    @RequestMapping("/index")
    public String index(Map<String, Object> map) {
        hiService.index("123");
        return "/index";
    }

    @RequestMapping("/log")
    public String log() {
        return "/log";
    }

}
