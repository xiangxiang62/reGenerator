package com.yupi.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.yupi.generator.MainGenerator;
import com.yupi.model.DataModel;
import lombok.Data;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;


@Command(name = "generate", description = "生成代码", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {

    /**
     * MySQL数据库配置
     */
    static DataModel.MysqlConfig mysqlConfig = new DataModel.MysqlConfig();

    @Command(name = "mysqlConfig")
    @Data
    public static class MysqlConfigCommand implements Runnable {
        @Option(names = {"--password"}, arity = "0..1", description = "密码", interactive = true, echo = true)
        private String password = "123456";
        @Option(names = {"--url"}, arity = "0..1", description = "地址", interactive = true, echo = true)
        private String url = "jdbc:mysql://localhost:3306/my_db";
        @Option(names = {"--username"}, arity = "0..1", description = "用户名", interactive = true, echo = true)
        private String username = "root";

        @Override
        public void run() {
            mysqlConfig.password = password;
            mysqlConfig.url = url;
            mysqlConfig.username = username;
        }
    }

    /**
     * 接口文档配置
     */
    static DataModel.DocsConfig docsConfig = new DataModel.DocsConfig();

    @Command(name = "docsConfig")
    @Data
    public static class DocsConfigCommand implements Runnable {
        @Option(names = {"--description"}, arity = "0..1", description = "接口文档描述", interactive = true, echo = true)
        private String description = "springboot-init";
        @Option(names = {"--title"}, arity = "0..1", description = "接口文档标题", interactive = true, echo = true)
        private String title = "接口文档";
        @Option(names = {"--version"}, arity = "0..1", description = "接口文档版本", interactive = true, echo = true)
        private String version = "1.0";

        @Override
        public void run() {
            docsConfig.description = description;
            docsConfig.title = title;
            docsConfig.version = version;
        }
    }

    @Option(names = {"--needDocs"}, arity = "0..1", description = "是否开启接口文档功能", interactive = true, echo = true)
    private boolean needDocs = true;

    @Option(names = {"--needPost"}, arity = "0..1", description = "是否开启帖子功能", interactive = true, echo = true)
    private boolean needPost = true;

    @Option(names = {"--needCors"}, arity = "0..1", description = "是否开启跨域功能", interactive = true, echo = true)
    private boolean needCors = true;

    @Option(names = {"--needEs"}, arity = "0..1", description = "是否开启ES功能", interactive = true, echo = true)
    private boolean needEs = true;

    @Option(names = {"--basePackage"}, arity = "0..1", description = "基础包名", interactive = true, echo = true)
    private String basePackage = "com.yupi";

    @Option(names = {"--needRedis"}, arity = "0..1", description = "是否开启Redis功能", interactive = true, echo = true)
    private boolean needRedis = true;

    public Integer call() throws Exception {
      System.out.println("输入MySQL数据库配置配置：");
      CommandLine mysqlConfigCommandLine = new CommandLine(MysqlConfigCommand.class);
      mysqlConfigCommandLine.execute("--password", "--url", "--username");
        if (needDocs) {
            System.out.println("输入接口文档配置配置：");
            CommandLine docsConfigCommandLine = new CommandLine(DocsConfigCommand.class);
            docsConfigCommandLine.execute("--description", "--title", "--version");
        }
        DataModel dataModel = new DataModel();
        BeanUtil.copyProperties(this, dataModel);
        dataModel.mysqlConfig = mysqlConfig;
        dataModel.docsConfig = docsConfig;
        MainGenerator.doGenerate(dataModel);
        return 0;
    }
}

