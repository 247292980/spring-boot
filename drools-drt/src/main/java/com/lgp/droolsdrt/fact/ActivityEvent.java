package com.lgp.droolsdrt.fact;

/**
 * 事件。命名规则约定：枚举命名长度不得超过15。
 */
public enum ActivityEvent {

    /**
     * 注册
     */

    REGISTER("注册", "regist", (byte) 1),

    /**
     * 注册
     */
    PARTNER_REGISTER("第三方注册", "prregist", (byte) 2),

    // /**
    // * 登录
    // */

    // LOGIN("登录", "login"),//登陆事件已废弃，原因：登陆直接作用在于首次登陆，首次登陆已被签到取代。

    /**
     * 签到
     */
    SIGN("签到", "sign", (byte) 3),

    /**
     * 绑定银行卡
     */
    BINDING_BANK("绑定银行卡", "bindbank", (byte) 4),

    /**
     * 绑定邮箱
     */
    BINDING_EMAIL("绑定邮箱", "bindemail", (byte) 5),

    /**
     * 绑定身份证，实名谁
     */
    BINDING_IDCARD("绑定身份证", "bindidcard", (byte) 6),

    /**
     * 充值
     */
    CHARGE("充值", "charge", (byte) 7),

    /**
     * 投资
     */

    INVEST("投资", "invest", (byte) 8),

    /**
     * 红包
     */
    RED_PACKET("红包","redpacket",(byte) 9);

    private String text;

    private String code;

    private Byte byteValue;

    ActivityEvent(String text, String code, byte byteValue) {
        this.text = text;
        this.code = code;
        this.byteValue = byteValue;
    }

    public String getText() {
        return text;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    public byte getByteValue() {
        return byteValue;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    public static ActivityEvent getByIntValue(byte byteValue) {
        ActivityEvent[] events = ActivityEvent.values();
        for (int i = 0; i < events.length; i++) {
            if (events[i].getByteValue() == byteValue) {
                return events[i];
            }
        }
        return null;
    }

}
