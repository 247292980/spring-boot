package com.lgp.droolsdrt.controller;

import com.lgp.droolsdrt.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @AUTHOR lgp
 * @DATE 2018/9/4 9:48
 * @DESCRIPTION 规则引擎接口
 **/

@Controller
@RequestMapping(value = "")
public class RuleController {


    @Autowired
    RuleService ruleService;

    @RequestMapping("/loadRule")
    public String loadRule(Map<String, Object> map) {
        ruleService.loadRule();
        map.put("hello", " loadRule");
        return "/index";

    }

    @RequestMapping("/useRule")
    public String useRule(Map<String, Object> map) {
        ruleService.useRule("123456", "13712750166");
        map.put("hello", " useRule");
        return "/index";
    }

    @RequestMapping("/useRule2")
    public String useRule2(Map<String, Object> map) {
        ruleService.useRule("123456", "13712750156");
        map.put("hello", " useRule2");
        return "/index";
    }
}
