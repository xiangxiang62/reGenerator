package com.yupi.springbootinit.model.enums;

/**
 * �û���ɫö��
 */
public enum UserRoleEnum {

    USER("�û�", "user"),
    ADMIN("����Ա", "admin"),
    BAN("�����", "ban");

    private final String text;

    private final String value;

    UserRoleEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
