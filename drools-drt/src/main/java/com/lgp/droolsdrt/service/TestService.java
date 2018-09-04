package com.lgp.droolsdrt.service;

import com.lgp.droolsdrt.domain.ActivityRule;
import com.lgp.droolsdrt.domain.RuleDTO;
import com.lgp.droolsdrt.mapper.ActivityRuleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @AUTHOR lgp
 * @DATE 2018/9/3 15:31
 * @DESCRIPTION
 **/
@Service
public class TestService {

    @Autowired
    ActivityRuleMapper activityRuleMapper;

    public List<ActivityRule> selectAll() {
        return activityRuleMapper.selectAll();
    }

    public ActivityRule select1() {
        return activityRuleMapper.selectByPrimaryKey(1);
    }

}
