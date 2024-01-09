package com.yupi.springbootinit.exception;

import com.yupi.springbootinit.common.ErrorCode;

/**
 * �Զ����쳣��
 */
public class BusinessException extends RuntimeException {

    /**
     * ������
     */
    private final int code;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    public int getCode() {
        return code;
    }
}
