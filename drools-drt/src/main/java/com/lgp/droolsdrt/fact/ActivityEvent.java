package com.lgp.droolsdrt.fact;

/**
 * 事件。命名规则约定：枚举命名长度不得超过15。
 */
public enum ActivityEvent {

    /**
     * 注册
     */

    REGISTER("注册", "regist", (byte) 1),

    ;

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
