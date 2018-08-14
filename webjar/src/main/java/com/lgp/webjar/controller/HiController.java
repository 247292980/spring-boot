package com.lgp.webjar.controller;

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
    @RequestMapping("/demo")
    public String demo() {
        return "/demo";
    }
}
