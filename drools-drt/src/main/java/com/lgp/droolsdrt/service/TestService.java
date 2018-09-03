package com.lgp.droolsdrt.service;

import com.lgp.droolsdrt.annotation.Fact;
import com.lgp.droolsdrt.domain.ActivityRule;
import com.lgp.droolsdrt.domain.RegisterMQDTO;
import com.lgp.droolsdrt.domain.RuleDTO;
import com.lgp.droolsdrt.engineer.RuleExecutor;
import com.lgp.droolsdrt.engineer.RuleExecutorResult;
import com.lgp.droolsdrt.fact.BaseFact;
import com.lgp.droolsdrt.fact.CustomerInfo;
import com.lgp.droolsdrt.fact.RegisterFact;
import com.lgp.droolsdrt.generator.RuleGenerator;
import com.lgp.droolsdrt.mapper.ActivityRuleMapper;
import com.lgp.droolsdrt.util.CommonUtil;
import com.lgp.droolsdrt.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @AUTHOR lgp
 * @DATE 2018/9/3 15:31
 * @DESCRIPTION
 **/
@Service
public class TestService {
    public static final Logger log = LoggerFactory.getLogger(TestService.class);

    @Autowired
    ActivityRuleMapper activityRuleMapper;

    public List<ActivityRule> selectAll() {
        return activityRuleMapper.selectAll();
    }

    public ActivityRule select1() {
        return activityRuleMapper.selectByPrimaryKey(1);
    }

    /**
     * 触发规则
     */
    public void useRule() {
        BaseFact fact = buildBaseFact("123456");
        String orderId = UUID.randomUUID().toString();
        RegisterMQDTO domain = new RegisterMQDTO();
        try {
            RuleExecutorResult ruleExecutorResult = doExecute(orderId, fact, domain);
            Assert.isTrue(ruleExecutorResult.getFailure() == 0, String.format("有%d条规则执行失败", ruleExecutorResult.getFailure()));
        } catch (IllegalArgumentException e) {
            log.error("FactEventHandler|handle|class={},orderId={}, userId={}, 规则执行异常:{}\n", this.getClass().getName(), orderId, "123456789", e.getMessage(), e);
        }
    }
    public RuleExecutorResult doExecute(String orderId, BaseFact fact, RegisterMQDTO domain) {
        RegisterFact registerFact = buildRegisterFact(domain);
        Fact factAnnotation = registerFact.getClass().getAnnotation(Fact.class);
        log.info("RegisterEventHandler|doExecute|{}事件的orderId={}, RegisterMQDTO={}", factAnnotation.value(), orderId, domain);
        CommonUtil.copyPropertiesCglib(fact, registerFact);
        return RuleExecutor.execute(registerFact, orderId);
    }
    /**
     * 加载规则
     */
    public void loadRule() {
        try {
            List<RuleDTO> ruleDTOs = getActivityRuleList();
            log.info("共有-{}-条加入规则引擎", ruleDTOs.size());
            if (!ruleDTOs.isEmpty()) {
                RuleGenerator generator = new RuleGenerator();
                generator.generateRules(ruleDTOs);
            }
        } catch (Exception e) {
            log.error("[规则引擎][加载][error]", e);
        }
    }

    /**
     * 从数据库里面取规则
     */
    public List<RuleDTO> getActivityRuleList() {
        Date begin = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant());

        List<ActivityRule> list = activityRuleMapper.selectAll();
        List<RuleDTO> ruleDTOList = new ArrayList<>();
        for (ActivityRule dto : list) {
            RuleDTO ruleDTO = new RuleDTO();
            ruleDTO.setBeginTime(begin);
            ruleDTO.setEndTime(end);
            ruleDTO.setRule(dto);
            ruleDTOList.add(ruleDTO);
        }

        return ruleDTOList;
    }

    public BaseFact buildBaseFact(String userId) {
        // 提前初始化好customerInfo, 避免累计投资金额 或者 累计投资天数 报null 异常
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setRegisterFrom("baidu");
        customerInfo.setRegisterTime(new Date());
        customerInfo.setExtenderUserId("654321");

        BaseFact fact = new BaseFact();
        fact.setUserName("test");
        fact.setUserId(userId);
        fact.setCust(customerInfo);
        return fact;
    }

    private RegisterFact buildRegisterFact(RegisterMQDTO domain) {
        RegisterFact registerFact = new RegisterFact();
        registerFact.setRegisterTime(DateUtil.localDateTimeToDate(domain.getRegisterTime()));
        CommonUtil.copyPropertiesCglib(domain, registerFact);
        return registerFact;
    }
}
