package com.lgp.aop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * @AUTHOR lgp
 * @DATE 2018/8/8 18:02
 * @DESCRIPTION 随机数字符串工具类
 **/
public class RandomStrUtil {
    private static final Logger logger = LoggerFactory.getLogger(RandomStrUtil.class);
    /**
     * 验证码字符串，去掉了1,0,i,o几个容易混淆的字符
     */
    private static final String VERIFICATION_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
    /**
     * 密码字符串
     */
    public static final String PWD_CODES = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890~!@#$%^&*.?";
    /**
     * 密码最少长度
     */
    public static final int PWD_MIN_LENGTH = 6;

    public static void main(String[] args) {
        logger.info("RandomStrUtil.getRandomPassword=" + getRandomPassword(8));
        logger.info("RandomStrUtil.getRandomPassword=" + getRandomPassword(8, 6));
        logger.info("RandomStrUtil.getCharRandom=" + getCharRandom(4));
        logger.info("RandomStrUtil.getCharRandom=" + getCharRandom(4, "asdfghjkl"));
        logger.info("RandomStrUtil.getNumberRandom=" + getNumberRandom(4));
    }

    /**
     * 生成指定长度的随机数字，不用数字字符串是因为性能。
     */
    public static String getNumberRandom(int len) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < len; i++) {
            int r = (int) (Math.random() * 10);
            stringBuffer.append(r);
        }
        return stringBuffer.toString();
    }

    /**
     * 指定源文件，生成指定长度的随机字符
     *
     * @param len        验证码长度
     * @param sourcesStr 验证码字符源
     */
    public static String getCharRandom(int len, String sourcesStr) {
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            verifyCode.append(sourcesStr.charAt(rand.nextInt(sourcesStr.length() - 1)));
        }
        return verifyCode.toString();
    }

    /**
     * 用默认源字符串，生成指定长度的随机字符
     *
     * @param len 验证码长度
     */
    public static String getCharRandom(int len) {
        return getCharRandom(len, VERIFICATION_CODES);
    }


    /**
     * 用默认源字符串，生成指定长度的随机密码，并规定密码最短长度
     *
     * @param len          验证码长度
     * @param pwdMinLength 密码最短长度
     */
    public static String getRandomPassword(int len, int pwdMinLength) {
        String result = "";
        if (len < pwdMinLength) {
        }
        while (true) {
            result = getCharRandom(len, PWD_CODES);
            if (result.matches(".*[a-z]{1,}.*") && result.matches(".*[A-Z]{1,}.*") && result.matches(".*\\d{1,}.*") && result.matches(".*[~!@#$%^&*\\.?]{1,}.*")) {
                return result;
            }
        }
    }

    /**
     * 用默认源字符串，生成指定长度的随机密码，并使用默认密码最短长度
     *
     * @param len 验证码长度
     */
    public static String getRandomPassword(int len) {
        return getRandomPassword(len, PWD_MIN_LENGTH);

    }
}
