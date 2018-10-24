package com.lgp.aop.aop2;

public interface ErrorCode {
    int MIN_BUSINESS_ERROR_STATUS = 600;
    int MAX_BUSINESS_ERROR_STATUS = 999;

    static boolean isBusinessStatus(int httpStatus) {
        return httpStatus >= 600 && httpStatus <= 999;
    }

    /** @deprecated */
    @Deprecated
    String getCode();

    int getStatus();

    String getMessage();
}
