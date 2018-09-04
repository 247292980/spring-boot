package com.lgp.droolsdrt.domain;


import com.lgp.droolsdrt.domain.fact.BaseFact;

/**
 * 丢到规则去匹配的数据
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
