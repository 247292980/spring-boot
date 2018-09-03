package com.lgp.droolsdrt.fact;

/**
 */
public class BaseFact {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 参加渠道
     */
    private Integer joinChannel;

    /**
     * 用户基本信息
     */
    private CustomerInfo cust;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public CustomerInfo getCust() {
        return cust;
    }

    public void setCust(CustomerInfo cust) {
        this.cust = cust;
    }

    public Integer getJoinChannel() {
        return joinChannel;
    }

    public void setJoinChannel(Integer joinChannel) {
        this.joinChannel = joinChannel;
    }

    /**
     * 校验非空字段
     *
     * @param fact
     * @return
     * @author xiaohua 2016年10月20日 下午10:15:34
     */
    public boolean validateNotNullField(BaseFact fact) {
        if (userId == null) {
            return true;
        }
        return false;
    }
}
