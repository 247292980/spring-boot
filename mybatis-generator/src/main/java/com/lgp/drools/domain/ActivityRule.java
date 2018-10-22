package com.lgp.drools.domain;

import java.util.Date;

public class ActivityRule {
    private Integer id;

    private Long taskId;

    private String event;

    private String ruleValue;

    private Byte priority;

    private Byte awardeeType;

    private Byte sendAwardTimes;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String isDelete;

    private String isSmsNotice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event == null ? null : event.trim();
    }

    public String getRuleValue() {
        return ruleValue;
    }

    public void setRuleValue(String ruleValue) {
        this.ruleValue = ruleValue == null ? null : ruleValue.trim();
    }

    public Byte getPriority() {
        return priority;
    }

    public void setPriority(Byte priority) {
        this.priority = priority;
    }

    public Byte getAwardeeType() {
        return awardeeType;
    }

    public void setAwardeeType(Byte awardeeType) {
        this.awardeeType = awardeeType;
    }

    public Byte getSendAwardTimes() {
        return sendAwardTimes;
    }

    public void setSendAwardTimes(Byte sendAwardTimes) {
        this.sendAwardTimes = sendAwardTimes;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    public String getIsSmsNotice() {
        return isSmsNotice;
    }

    public void setIsSmsNotice(String isSmsNotice) {
        this.isSmsNotice = isSmsNotice == null ? null : isSmsNotice.trim();
    }
}