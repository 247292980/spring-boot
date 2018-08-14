package com.lgp.drools.demo;

import com.lgp.drools.pojo.Message;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

/**
 * 用来区分StatelessKieSession和KieSession
 */
public class StatelessTest {
    protected KieServices kieServices;

    protected KieContainer kieContainer;

    protected KieSession kieSession;
    protected StatelessKieSession statelessKieSession;

    @Before
    public void init() {
//从Factory得到Services
        kieServices = KieServices.Factory.get();
//        在类路径中找到的所有DRL文件
        kieContainer = kieServices.getKieClasspathContainer();
//得到kmodule的ksession
        kieSession = kieContainer.newKieSession("ksession-rules");
//stateless必须注释kmodule里全部的kbase
//		statelessKieSession=kieContainer.newStatelessKieSession();
    }

    //kieSession和statelessKieSession的调用是不一样的
    @Test
    public void testKieSession() {
        Message message = new Message();
        message.setMessage("Hello World");
        message.setStatus(Message.HELLO);

        kieSession.insert(message);
        kieSession.fireAllRules();
    }

    @Test
    public void testStatelessKieSession() {
        Message message = new Message();
        message.setMessage("Hello World");
        message.setStatus(Message.HELLO);

        statelessKieSession.execute(message);

    }
}
