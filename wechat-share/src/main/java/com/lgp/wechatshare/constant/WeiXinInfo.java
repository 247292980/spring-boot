package com.lgp.wechatshare.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @AUTHOR lgp
 * @DATE 2018/4/16 21:28
 * @DESCRIPTION
 **/
@Component
public class WeiXinInfo {
    @Value("${appID:123456789}")
    private String appID;
    @Value("${appSecret:123456789}")
    private String appSecret;
    @Value("${outhUrl:http://weixin.xxxxx.com/Activity/WXAuthor.aspx?ReturnUrl=}")
    private String outhUrl;

    /*静默授权，不弹框确认*/
    private String snsapiUserinfo = "snsapi_userinfo";
    /*普通授权，弹框确认*/
    private String snsapiBase = "snsapi_base";

    private String refreshToken = "refresh_token";

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getOuthUrl() {
        return outhUrl;
    }

    public void setOuthUrl(String outhUrl) {
        this.outhUrl = outhUrl;
    }

    public String getSnsapiUserinfo() {
        return snsapiUserinfo;
    }

    public void setSnsapiUserinfo(String snsapiUserinfo) {
        this.snsapiUserinfo = snsapiUserinfo;
    }

    public String getSnsapiBase() {
        return snsapiBase;
    }

    public void setSnsapiBase(String snsapiBase) {
        this.snsapiBase = snsapiBase;
    }
}
