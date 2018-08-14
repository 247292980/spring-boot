package com.lgp.fusioncharts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @AUTHOR lgp
 * @DATE 2018/7/31 9:28
 * @DESCRIPTION
 **/
@RequestMapping("")
@Controller
public class RouterController {
    @RequestMapping(value = "/index")
    public String index() {
        String page = "index";
        return page;
    }
    @RequestMapping(value = "/json")
    public String json() {
        String page = "json";
        return page;
    }
}
