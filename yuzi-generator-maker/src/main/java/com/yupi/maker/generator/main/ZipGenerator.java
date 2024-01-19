package com.yupi.maker.generator.main;

/**
 * 生成代码生成器压缩包
 */
public class ZipGenerator extends GenerateTemplate {

    @Override
    protected String buildDist(String ouputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) {
        String disPath = super.buildDist(ouputPath,sourceCopyDestPath,jarPath,shellOutputFilePath);
        return super.buildZip(disPath);
    }

}
