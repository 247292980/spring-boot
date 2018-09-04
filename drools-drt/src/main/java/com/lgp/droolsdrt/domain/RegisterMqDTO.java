package com.lgp.droolsdrt.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * @AUTHOR lgp
 * @DATE 2018/6/29 11:15
 * @DESCRIPTION 消息中间件的信息结构
 **/
public class RegisterMqDTO {
    private String userId;
    private String telephone;
    /*format yyyy-MM-dd hh:mm:ss
     * */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registerTime;
    private String tdfrom;
    private String extenderUserId;
    private String extendKey;

    private String callBackParams;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }

    public String getTdfrom() {
        return tdfrom;
    }

    public void setTdfrom(String tdfrom) {
        this.tdfrom = tdfrom;
    }

    public String getExtenderUserId() {
        return extenderUserId;
    }

    public void setExtenderUserId(String extenderUserId) {
        this.extenderUserId = extenderUserId;
    }

    public String getExtendKey() {
        return extendKey;
    }

    public void setExtendKey(String extendKey) {
        this.extendKey = extendKey;
    }

    public String getCallBackParams() {
        return callBackParams;
    }

    public void setCallBackParams(String callBackParams) {
        this.callBackParams = callBackParams;
    }

}
