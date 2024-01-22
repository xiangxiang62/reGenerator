package com.yupi.web.model.dto.generator;

import lombok.Data;

import java.util.Map;

@Data
public class GeneratorUseRequest {

    /**
     * 生成器的 id
     */
    private long id;


    /**
     * 数据模型
     */
    private Map<String,Object> dataModel;

    private static final long serialVersionUID = 1L;
}
