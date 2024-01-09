package com.yupi.springbootinit.common;

/**
 * �Զ��������
 */
public enum ErrorCode {

    SUCCESS(0, "ok"),
    PARAMS_ERROR(40000, "�����������"),
    NOT_LOGIN_ERROR(40100, "δ��¼"),
    NO_AUTH_ERROR(40101, "��Ȩ��"),
    NOT_FOUND_ERROR(40400, "�������ݲ�����"),
    FORBIDDEN_ERROR(40300, "��ֹ����"),
    SYSTEM_ERROR(50000, "ϵͳ�ڲ��쳣"),
    OPERATION_ERROR(50001, "����ʧ��");

    /**
     * ״̬��
     */
    private final int code;

    /**
     * ��Ϣ
     */
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
