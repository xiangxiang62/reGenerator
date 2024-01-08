package com.yupi.maker.model;

import lombok.Data;

/**
 * 动态模板配置
 */
@Data
public class DataModel {

    /**
     * 是否生成循环
     */
    private boolean loop;

    /**
     * 作者信息
     */
    private String author = "yupi";

    /**
     * 输出信息
     */
    private String outputText = "sum = ";

}
