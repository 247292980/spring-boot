package com.lgp.springsecurity.controller;

import com.lgp.springsecurity.domain.result.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @AUTHOR lgp
 * @DATE 2018/1/18 14:44
 * @DESCRIPTION
 **/
@Controller
public class HomeController {
    @RequestMapping("/")
    public String home(Model model) {
        Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "index";
    }
    @RequestMapping("/index")
    public String index(Model model) {
        Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "index";
    }
    @RequestMapping("/login")
    public String login(Model model) {
        Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "login";
    }
    @RequestMapping("/logout")
    public String logout(Model model) {
        Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "login";
    }
}


