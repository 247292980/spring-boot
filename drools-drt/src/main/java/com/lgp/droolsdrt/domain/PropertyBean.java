package com.lgp.droolsdrt.domain;

/**
 * @AUTHOR lgp
 * @DATE 2018/9/3 16:10
 * @DESCRIPTION 属性描述类
 **/
public class PropertyBean {

    private String name;

    private String format;

    private String optionalValue;

    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getOptionalValue() {
        return optionalValue;
    }

    public void setOptionalValue(String optionalValue) {
        this.optionalValue = optionalValue;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
