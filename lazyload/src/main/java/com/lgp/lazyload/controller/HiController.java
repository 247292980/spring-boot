package com.lgp.lazyload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @AUTHOR lgp
 * @DATE 2018/10/22 10:31
 * @DESCRIPTION
 **/
@RequestMapping("")
@Controller
public class HiController {
    /**
     * 返回html模板.
     */
    @RequestMapping("/index")
    public String index() {
        return "/index";
    }
}
