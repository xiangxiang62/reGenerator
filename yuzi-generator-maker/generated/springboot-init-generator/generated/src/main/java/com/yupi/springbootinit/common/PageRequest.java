package com.yupi.springbootinit.common;

import lombok.Data;

/**
 * ��ҳ����
 */
@Data
public class PageRequest {

    /**
     * ��ǰҳ��
     */
    private long current = 1;

    /**
     * ҳ���С
     */
    private long pageSize = 10;
}
