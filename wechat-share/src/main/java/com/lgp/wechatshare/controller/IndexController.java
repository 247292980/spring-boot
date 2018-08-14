package com.lgp.wechatshare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(value = "/share")
    public String share(Model model) {
        System.out.println("to share.html");
        return "share";
    }

    @RequestMapping(value = "/index")
    public String index(Model model) {
        System.out.println("to index.html");
        return "index";
    }
}
