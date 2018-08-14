package com.lgp.drools.demo;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * sampl.bpmn
 */
public class ProcessTest {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession kSession = kContainer.newKieSession("ksession-process");

            // start a new process instance
            kSession.startProcess("com.sample.bpmn.hello");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
