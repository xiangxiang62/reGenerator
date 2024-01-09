package com.yupi.springbootinit.model.dto.user;

import java.io.Serializable;
import lombok.Data;

/**
 * �û���������
 */
@Data
public class UserUpdateRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * �û��ǳ�
     */
    private String userName;

    /**
     * �û�ͷ��
     */
    private String userAvatar;

    /**
     * ���
     */
    private String userProfile;

    /**
     * �û���ɫ��user/admin/ban
     */
    private String userRole;

    private static final long serialVersionUID = 1L;
}