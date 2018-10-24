package com.lgp.aop.aop2;

public class AppBusinessException extends RuntimeException {
    public static final ErrorCode DEFAULT_CODE;
    private String code;
    private int httpStatus;

    private AppBusinessException(String code, int httpStatus, String message) {
        super(message);
        this.code = DEFAULT_CODE.getCode();
        this.httpStatus = DEFAULT_CODE.getStatus();
        this.code = code;
        this.httpStatus = httpStatus;
    }

    /** @deprecated */
    @Deprecated
    public AppBusinessException(String message) {
        super(message);
        this.code = DEFAULT_CODE.getCode();
        this.httpStatus = DEFAULT_CODE.getStatus();
    }

    public AppBusinessException(ErrorCode errorCode, String message) {
        this(errorCode.getCode(), errorCode.getStatus(), message);
    }

    public AppBusinessException(ErrorCode errorCode) {
        this(errorCode, errorCode.getMessage());
    }

    public String getCode() {
        return this.code;
    }

    public int getHttpStatus() {
        return this.httpStatus;
    }

    static {
        DEFAULT_CODE = CommonErrorCode.INTERNAL_ERROR;
    }
}