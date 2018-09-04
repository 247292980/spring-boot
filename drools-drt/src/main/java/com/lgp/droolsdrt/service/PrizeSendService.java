package com.lgp.droolsdrt.service;

import com.alibaba.fastjson.JSON;
import com.lgp.droolsdrt.engineer.RuleExecuteGlobal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @AUTHOR lgp
 * @DATE 2018/9/4 11:20
 * @DESCRIPTION
 **/
@Service
public class PrizeSendService {
    private Logger logger = LoggerFactory.getLogger(PrizeSendService.class);

    public void sendAward(RuleExecuteGlobal global, Integer awardeeType) {
        logger.info("PrizeSendService|sendAward|发放奖品|global={}|awardeeType={}", JSON.toJSON(global), awardeeType);
        global.getResult().addCount();

        switch (awardeeType) {
            case 1:
                System.out.println("老板，内部操作为一等奖");
                global.getResult().addSuccess();
                break;
            case 2:
                System.out.println("员工，就你也想得奖？");
                global.getResult().addSuccess();
                break;
            default:
                System.out.println("出错了！！1");
                global.getResult().addFailure();
                break;
        }
    }
}
