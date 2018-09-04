package com.lgp.droolsdrt.fact;

import com.lgp.droolsdrt.annotation.Fact;
import com.lgp.droolsdrt.annotation.FactProperty;
import com.lgp.droolsdrt.annotation.NotNull;

import java.util.Date;


/**
 * @AUTHOR lgp
 * @DATE 2018/6/28 17:18
 * @DESCRIPTION 注册fact
 **/
@Fact(ActivityEvent.REGISTER)
public class RegisterFact extends BaseFact {

    @NotNull
    @FactProperty(desc = "用户ID", format = "例：userId == &quot;08b52da9-8e63-48bd-8250-63a04ee9e701&quot;", optionalValue = {"用户ID"})
    private String userId;

    @NotNull
    @FactProperty(desc = "手机号", format = "例：telephone == &quot;13313313322&quot;", optionalValue = {"手机号"})
    private String telephone;

    /*format yyyy-MM-dd hh:mm:ss
     * */
    @NotNull
    @FactProperty(desc = "注册时间", format = "例：registerTime &lt;= 2016-06-06 10:00:00&quot;", optionalValue = {"yyyy-MM-dd HH:mm:ss"})
    private Date registerTime;
    @FactProperty(desc = "注册渠道", format = "例：tdfrom == &quot;anniversary2018&quot;", optionalValue = {"注册渠道"})
    private String tdfrom;
    @FactProperty(desc = "用户邀请人ID", format = "例：extenderUserId == &quot;08b52da9-8e63-48bd-8250-63a04ee9e701&quot;", optionalValue = {"用户邀请人ID"})
    private String extenderUserId;
    @FactProperty(desc = "用户邀请人邀请码", format = "例：extenderKey == &quot;08b52da9-8e63-48bd-8250-63a04ee9e701&quot;", optionalValue = {"用户邀请人邀请码"})
    private String extenderKey;

    @FactProperty(desc = "通用三方平台回调参数", format = "例：callBackParams == &quot;xxxxxx&quot;", optionalValue = {"通用三方平台回调参数"})
    private String callBackParams;

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
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

    public String getExtenderKey() {
        return extenderKey;
    }

    public void setExtenderKey(String extenderKey) {
        this.extenderKey = extenderKey;
    }

    public String getCallBackParams() {
        return callBackParams;
    }

    public void setCallBackParams(String callBackParams) {
        this.callBackParams = callBackParams;
    }
}
