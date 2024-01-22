package com.yupi.cli.command;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.yupi.generator.MainGenerator;
import com.yupi.model.DataModel;
import lombok.Data;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;


@Command(name = "json-generate", description = "读取 json 文件生成代码", mixinStandardHelpOptions = true)
@Data
public class JsonGenerateCommand implements Callable<Integer> {

    @Option(names = {"-f", "--file"}, arity = "0..1", description = "json 文件路径", interactive = true, echo = true)
    private String filePath;

    public Integer call() throws Exception {
        String jsonStr = FileUtil.readUtf8String(filePath);
        DataModel dataModel = JSONUtil.toBean(jsonStr, DataModel.class);
        MainGenerator.doGenerate(dataModel);
        return 0;
    }
}