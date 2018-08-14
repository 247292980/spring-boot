package com.lgp.vue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @AUTHOR lgp
 * @DATE 2018/7/31 15:25
 * @DESCRIPTION
 **/
@RequestMapping("")
@Controller
public class HiController {
    /**
     * 返回html模板.
     */
    @RequestMapping("/index")
    public String index(Map<String, Object> map) {
        return "/index";
    }
    /**
     * 返回html模板.
     */
    @RequestMapping("/mvvc")
    public String mvvc(Map<String, Object> map) {
        return "/mvvc";
    }
}
