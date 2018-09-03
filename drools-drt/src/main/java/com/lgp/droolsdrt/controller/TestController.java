package com.lgp.droolsdrt.controller;

import com.alibaba.fastjson.JSON;
import com.lgp.droolsdrt.domain.ActivityRule;
import com.lgp.droolsdrt.mapper.ActivityRuleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @AUTHOR lgp
 * @DATE 2018/9/3 14:46
 * @DESCRIPTION
 **/
@Controller
@RequestMapping(value = "")
public class TestController {
    public static final Logger log = LoggerFactory.getLogger(TestController.class);
    @Autowired
    ActivityRuleMapper activityRuleMapper;

    @RequestMapping("/mysql")
    public String mysql(Map<String, Object> map) {
        ActivityRule activityRule = activityRuleMapper.selectByPrimaryKey(1);
        map.put("hello", JSON.toJSON(activityRule));
        log.info("activityRule={}", JSON.toJSON(activityRule));
        return "/index";
    }

    @RequestMapping("/index")
    public String index(Map<String, Object> map) {
        log.info("index");
        map.put("hello", "from HiController map");
        return "/index";
    }
}
