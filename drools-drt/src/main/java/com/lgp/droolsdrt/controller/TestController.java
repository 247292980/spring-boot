package com.lgp.droolsdrt.controller;

import com.alibaba.fastjson.JSON;
import com.lgp.droolsdrt.domain.ActivityRule;
import com.lgp.droolsdrt.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @AUTHOR lgp
 * @DATE 2018/9/3 14:46
 * @DESCRIPTION 测试接口 mysql h5
 **/
@Controller
@RequestMapping(value = "")
public class TestController {
    public static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    TestService testService;

    @RequestMapping("/select1")
    public String select1(Map<String, Object> map) {
        ActivityRule activityRule = testService.select1();
        map.put("hello", JSON.toJSON(activityRule));
        log.info("activityRule={}", JSON.toJSON(activityRule));
        return "/index";
    }

    @RequestMapping("/selectAll")
    public String selectAll(Map<String, Object> map) {
        List<ActivityRule> list = testService.selectAll();
        map.put("hello", JSON.toJSON(list));
        log.info("list={}", JSON.toJSON(list));
        return "/index";
    }

    @RequestMapping("/index")
    public String index(Map<String, Object> map) {
        log.info("index");
        map.put("hello", "from HiController map");
        return "/index";
    }
}
