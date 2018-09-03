package com.lgp.droolsdrt.engineer;


import com.lgp.droolsdrt.fact.BaseFact;

/**
 * Created by amos.zhou on 19:24.
 */
public class RuleExecuteGlobal {

    /**
     * 发送用户id
     */
    private String userId;

    /**
     * 流水号
     */
    private String orderId;

    private BaseFact factObj;

    private RuleExecutorResult result;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BaseFact getFactObj() {
        return factObj;
    }

    public void setFactObj(BaseFact factObj) {
        this.factObj = factObj;
    }

    public RuleExecutorResult getResult() {
        return result;
    }

    public void setResult(RuleExecutorResult result) {
        this.result = result;
    }

}
