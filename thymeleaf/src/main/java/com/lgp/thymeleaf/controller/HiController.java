package com.lgp.thymeleaf.controller;

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
        map.put("hello", "from HiController map");
        Map<String, Object> temp = new HashMap();
        temp.put("hi", "from HiController new map");
        /*不用写后缀*/
        return "/index";
    }
}
