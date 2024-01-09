package com.yupi.springbootinit.model.dto.user;

import com.yupi.springbootinit.common.PageRequest;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * �û���ѯ����
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends PageRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * �û��ǳ�
     */
    private String userName;

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