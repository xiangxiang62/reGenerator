package com.yupi.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * �û�
 */
@TableName(value = "user")
@Data
public class User implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * �û��˺�
     */
    private String userAccount;

    /**
     * �û�����
     */
    private String userPassword;

    /**
     * �û��ǳ�
     */
    private String userName;

    /**
     * �û�ͷ��
     */
    private String userAvatar;

    /**
     * �û����
     */
    private String userProfile;

    /**
     * �û���ɫ��user/admin/ban
     */
    private String userRole;

    /**
     * ����ʱ��
     */
    private Date createTime;

    /**
     * ����ʱ��
     */
    private Date updateTime;

    /**
     * �Ƿ�ɾ��
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}