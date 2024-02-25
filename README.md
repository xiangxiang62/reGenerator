# 定制化代码生成器

### 项目介绍

基于 React + Spring Boot +PicoCli + Cos 对象存储的定制化代码生成器。

开发者可以在平台上传制作并发布代码生成器，用户可以搜索、下载、在线使用代码生成器，管理员可以集中管理所有用户和生成器的信息。



![pFaspSU.png](https://s11.ax1x.com/2024/02/26/pFaspSU.png)



### 前端

- Ant Design Pro
- Node 18
- OpenAPI 前端代码生成

#### 运行方式：

1. 使用 `npm install` 安装依赖。
2. 打开终端，输入 `npm run dev` 运行即可。

### 后端

- Spring Boot 框架
- MySQL 数据库
- MyBatis
- PicoCli 命令行应用开发
- FreeMaker 模板引擎
- Caffeine + Redis 多级缓存
- XXL-JOB 分布式任务调度系统
- **设计模式**
  - 命令模式
  - 模板方法模式
  - 双检锁单例模式
- Vert.x 响应式编程
- JMeter 压力测试
- **工具（包）**
  - Hutool
  - Java 8 Lambda 表达式

#### 运行方式：

1. 更改 `application.yml` 中的数据库为本地数据库配置。
2. 在 `sql` 文件中执行初始化 sql 文件，生成表结构。
3. 配置本地 Redis（若没有，需要安装 Redis）。
4. 运行 `web-backend` 主程序即可。

> 可配合 Knife4j 接口文档进行接口测试。
>
> http://localhost:[端口号]/api/doc.html 。
