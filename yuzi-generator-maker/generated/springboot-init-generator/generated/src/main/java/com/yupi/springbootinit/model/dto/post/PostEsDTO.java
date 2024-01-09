package com.yupi.springbootinit.model.dto.post;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ���� ES ��װ��
 **/
// todo ȡ��ע�Ϳ��� ES���������� ES��
//@Document(indexName = "post")
@Data
public class PostEsDTO implements Serializable {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    /**
     * id
     */
    @Id
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

    /**
     * �����û� id
     */
    private Long userId;

    /**
     * ����ʱ��
     */
    @Field(index = false, store = true, type = FieldType.Date, format = {}, pattern = DATE_TIME_PATTERN)
    private Date createTime;

    /**
     * ����ʱ��
     */
    @Field(index = false, store = true, type = FieldType.Date, format = {}, pattern = DATE_TIME_PATTERN)
    private Date updateTime;

    /**
     * �Ƿ�ɾ��
     */
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}
