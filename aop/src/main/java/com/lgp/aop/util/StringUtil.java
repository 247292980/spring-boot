package com.lgp.aop.util;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * @AUTHOR lgp
 * @DATE 2018/8/8 18:14
 * @DESCRIPTION 字符串工具类
 **/
public class StringUtil {

    /**
     * 手机号正则表达式
     */
    private static final String PHONE_REGEX = "^(1[1-9]{1})[0-9]{9}$";

    /**
     * 验证是密码
     *
     * @param password
     * @return
     */
    public static boolean isPassword(String password) {
        return !isNotPassword(password);
    }

    /**
     * 验证不是密码
     *
     * @param password
     * @return
     */
    public static boolean isNotPassword(String password) {
        return (StringUtil.isNotPhone(password) && (password.length() < 6 || password.length() > 16));
    }

    /**
     * 验证不是手机号
     *
     * @param phoneNum
     */
    public static boolean isNotPhone(String phoneNum) {
        return !isPhone(phoneNum);
    }

    /**
     * 验证手机号
     *
     * @param phoneNum
     */
    public static boolean isPhone(String phoneNum) {
        return phoneNum.matches(PHONE_REGEX);
    }

    /**
     * 判断是否不为空
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 判断是否为空
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 与SQL SERVER的newId函数一致
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().toUpperCase();
    }

    /**
     * 与SQL SERVER的newId函数一致
     */
    public static String getEmptyUUID() {
        return "00000000-0000-0000-0000-000000000000";
    }

    /**
     * emoji表情替换
     *
     * @param source  原字符串
     * @param slipStr emoji表情替换成的字符串
     */
    public static String filterEmoji(String source, String slipStr) {
        if (StringUtils.isNotBlank(source)) {
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", slipStr);
        } else {
            return source;
        }
    }
}
