package com.yupi;

import com.yupi.cli.CommandExecutor;
import com.yupi.generator.StaticGenerator;

import java.io.File;

public class Main {
    //public static void main(String[] args) {
/*        // 获取到文件的根路径
        String projectPath = System.getProperty("user.dir");
        //
        File parentPath = new File(projectPath).getParentFile();
        // ACM 模板文件
        String inputPath = new File(parentPath, "yuzi-generator-demo-projects/acm-template").getAbsolutePath();
        String outputPath = projectPath;
        StaticGenerator.copyFilesByHutool(inputPath,outputPath);*/



        //}
        public static void main(String[] args) {
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);
    }
    }
