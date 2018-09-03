package com.lgp.droolsdrt.domain;

import java.util.Date;
import java.util.List;


/**
 * 规则的数据结构
 */
public class RuleDTO {

    private ActivityRule rule;
    private Date beginTime;
    private Date endTime;
    private List<Integer> joinChannel;

    public ActivityRule getRule() {
        return rule;
    }

    public void setRule(ActivityRule rule) {
        this.rule = rule;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<Integer> getJoinChannel() {
        return joinChannel;
    }

    public void setJoinChannel(List<Integer> joinChannel) {
        this.joinChannel = joinChannel;
    }

    @Override
    public String toString() {
        return "RuleDTO{" +
                "rule=" + rule +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", joinChannel=" + joinChannel +
                '}';
    }
}
