package com.lgp.droolsdrt.executor;

import com.alibaba.fastjson.JSON;
import com.lgp.droolsdrt.domain.RuleExecuteGlobal;
import com.lgp.droolsdrt.domain.RuleExecutorResult;
import com.lgp.droolsdrt.domain.fact.BaseFact;
import org.drools.compiler.kproject.ReleaseIdImpl;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 规则执行器
 */
public class RuleExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(RuleExecutor.class);
    static ReleaseId releaseId = new ReleaseIdImpl("com.lgp.droolsdrt", "drools-drt", "1.0");
    private static KieBase kieBase;


    /**
     * 单例(非线程安全,避免影响性能)
     *
     * @return
     * @author xiaohua 2016年10月24日 下午2:08:32
     */
    public static KieBase getKieBase() {
        if (kieBase == null) {
            KieServices kieServices = KieServices.Factory.get();
            KieContainer kieContainer = kieServices.newKieContainer(getReleaseId());
            kieBase = kieContainer.getKieBase();
        }
        return kieBase;
    }

    /**
     * 更新kieBase
     *
     * @return
     * @author xiaohua 2016年11月8日 下午7:42:04
     */
    public static KieBase updateKieBase() {
        //充值kiebase
        kieBase = null;
        return getKieBase();
    }

    /**
     * modify by xiaohua
     * KieBase被抽取
     *
     * @param fact
     * @param orderId
     * @return 规则执行结果
     * @author xiaohua 2016年10月24日 下午2:09:12
     */
    public static RuleExecutorResult execute(BaseFact fact, String orderId) {
        LOGGER.info("RuleExecutor|execute|fact={}", JSON.toJSON(fact));
        StatelessKieSession statelessKieSession = getKieBase().newStatelessKieSession();
        RuleExecuteGlobal global = new RuleExecuteGlobal();
        global.setUserId(fact.getUserId());
        global.setOrderId(orderId);
        global.setFactObj(fact);
        global.setResult(new RuleExecutorResult());
        statelessKieSession.getGlobals().set("globalParams", global);
        statelessKieSession.execute(fact);

        return global.getResult();
    }

    public static ReleaseId getReleaseId() {
        return releaseId;
    }

}
