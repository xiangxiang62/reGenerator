package com.yupi.springbootinit.model.dto.post;

import com.yupi.springbootinit.common.PageRequest;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ��ѯ����
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PostQueryRequest extends PageRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * id
     */
    private Long notId;

    /**
     * ������
     */
    private String searchText;

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

    /**
     * ������һ����ǩ
     */
    private List<String> orTags;

    /**
     * �����û� id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}