package com.yupi.springbootinit.model.dto.user;

import java.io.Serializable;
import lombok.Data;

/**
 * ÓÃ»§µÇÂ¼ÇëÇó
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private String userAccount;

    private String userPassword;
}
