package com.yupi.springbootinit.model.dto.user;

import java.io.Serializable;
import lombok.Data;

/**
 * �û���������
 */
@Data
public class UserAddRequest implements Serializable {

    /**
     * �û��ǳ�
     */
    private String userName;

    /**
     * �˺�
     */
    private String userAccount;

    /**
     * �û�ͷ��
     */
    private String userAvatar;

    /**
     * �û���ɫ: user, admin
     */
    private String userRole;

    private static final long serialVersionUID = 1L;
}