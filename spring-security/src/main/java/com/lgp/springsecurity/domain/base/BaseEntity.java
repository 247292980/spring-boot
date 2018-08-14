package com.lgp.springsecurity.domain.base;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @AUTHOR lgp
 * @DATE 2018/1/18 11:45
 * @DESCRIPTION
 **/
public class BaseEntity implements Serializable {
    @Override
    public String toString() {
        String json = JSONObject.toJSONString(this);
        return json;
    }
}
