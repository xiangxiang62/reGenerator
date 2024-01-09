package com.yupi.springbootinit.model.dto.post;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * ��������
 */
@Data
public class PostUpdateRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * ����
     */
    private String title;

    /**
     * ����
     */
    private String content;

    /**
     * ��ǩ�б�
     */
    private List<String> tags;

    private static final long serialVersionUID = 1L;
}