package com.yupi.model;

import lombok.Data;


/**
 * 数据模型
 */
@Data
public class DataModel {

    /**
     * MySQL数据库配置
     */
    public MysqlConfig mysqlConfig = new MysqlConfig();

    /**
     * 用于生成MySQL数据库配置
     */
    @Data
    public static class MysqlConfig {
        /**
         * 密码
         */
        public String password = "123456";
        /**
         * 地址
         */
        public String url = "jdbc:mysql://localhost:3306/my_db";
        /**
         * 用户名
         */
        public String username = "root";
    }


    /**
     * 接口文档配置
     */
    public DocsConfig docsConfig = new DocsConfig();

    /**
     * 用于生成接口文档配置
     */
    @Data
    public static class DocsConfig {
        /**
         * 接口文档描述
         */
        public String description = "springboot-init";
        /**
         * 接口文档标题
         */
        public String title = "接口文档";
        /**
         * 接口文档版本
         */
        public String version = "1.0";
    }


    /**
     * 是否开启接口文档功能
     */
    public boolean needDocs = true;

    /**
     * 是否开启帖子功能
     */
    public boolean needPost = true;

    /**
     * 是否开启跨域功能
     */
    public boolean needCors = true;

    /**
     * 是否开启ES功能
     */
    public boolean needEs = true;

    /**
     * 基础包名
     */
    public String basePackage = "com.yupi";

    /**
     * 是否开启Redis功能
     */
    public boolean needRedis = true;
}