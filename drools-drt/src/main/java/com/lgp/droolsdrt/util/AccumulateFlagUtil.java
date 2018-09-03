package com.lgp.droolsdrt.util;

/**
 *
 **/
public class AccumulateFlagUtil {


    public static AccumulateFlag getAccumulateFlagByRule(String rule) {
        return AccumulateFlag.valueOfByRule(rule);
    }

    public enum AccumulateFlag {
        NO_ACCUMULATE(0, "不统计累计", "NO_ACCUMULATE"),
        ACCUMULATE_INVEST_AMOUNT(1, "累计投资金额", "cust.totalInvestAmount"),
        ACCUMULATE_SIGN_DAYS(2, "累计签到天数", "cust.totalSignedDays");

        private int code;
        private String desc;
        private String pattern;

        AccumulateFlag(int code, String desc, String pattern) {
            this.code = code;
            this.desc = desc;
            this.pattern = pattern;
        }

        public static AccumulateFlag valueOfByRule(String rule) {
            if (null == rule || "".equals(rule)) {
                return AccumulateFlag.NO_ACCUMULATE;
            }

            for (AccumulateFlag flag : values()) {
                if (rule.contains(flag.getPattern())) {
                    return flag;
                }
            }

            return AccumulateFlag.NO_ACCUMULATE;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        public String getPattern() {
            return pattern;
        }
    }
}
