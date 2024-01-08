package com.yupi.maker.generator.file;


import com.yupi.maker.model.DataModel;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 核心生成器
 *
 */
public class MainGenerator {

    public static void doGenerate(Object model) throws TemplateException, IOException {
        String projectPath = System.getProperty("user.dir");
        // 这个项目的根目录
        File parentFile = new File(projectPath).getParentFile();

        // 输入路径
        String inputPath = new File(parentFile,"yuzi-generator-demo-projects/acm-template").getAbsolutePath();
        String outputPath = projectPath;

        // 生成静态文件
        StaticGenerator.copyFilesByHutool(inputPath,outputPath);

        // 生成动态文件
        String inputDynamicFilePath = projectPath + File.separator + "src/main/resources/templates/Maintemplate.java.ftl";
        String outputDynamicFilePath = outputPath + File.separator + "acm-template/src/com/yupi/acm/Maintemplate.java";

        DynamicFileGenerator.doGenerate(inputDynamicFilePath,outputDynamicFilePath,model);
    }



    public static void main(String[] args) throws TemplateException, IOException {
        DataModel mainTemplateConfig = new DataModel();
        mainTemplateConfig.setOutputText("sum = ");
        mainTemplateConfig.setLoop(true);
        mainTemplateConfig.setAuthor("author_xiang");
        doGenerate(mainTemplateConfig);
    }

}
