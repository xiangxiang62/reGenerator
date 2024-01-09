package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.springbootinit.model.dto.user.UserQueryRequest;
import com.yupi.springbootinit.model.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * �û�����
 */
public interface UserService extends IService<User> {

    /**
     * �û�ע��
     *
     * @param userAccount   �û��˻�
     * @param userPassword  �û�����
     * @param checkPassword У������
     * @return ���û� id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * �û���¼
     *
     * @param userAccount  �û��˻�
     * @param userPassword �û�����
     * @param request
     * @return ��¼�û���Ϣ
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * ��ȡ��ǰ��¼�û�
     *
     * @param request
     * @return
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * �Ƿ�Ϊ����Ա
     *
     * @param request
     * @return
     */
    boolean isAdmin(HttpServletRequest request);

    /**
     * �Ƿ�Ϊ����Ա
     *
     * @param user
     * @return
     */
    boolean isAdmin(User user);

    /**
     * �û�ע��
     *
     * @param request
     * @return
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * ��ȡ��ѯ����
     *
     * @param userQueryRequest
     * @return
     */
    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);

}
