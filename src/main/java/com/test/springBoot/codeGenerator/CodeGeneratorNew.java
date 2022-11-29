package com.test.springBoot.codeGenerator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author wangyinjia
 * @description
 * @date 2021/12/15
 */
public class CodeGeneratorNew {

    public static void main(String[] args) {
        String url = "jdbc:mysql://ip:3306/p2p?useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8";
        String username = "aa";
        String password = "aa";
        String userDir = System.getProperty("user.dir");
        String javaUrl = userDir+"/src/main/java";
        String mapperUrl = userDir+"/src/main/resources/mapper";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("baomidou") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
                            .outputDir(javaUrl); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.test.springBoot") // 设置父包名
                            .moduleName("cc") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperUrl)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("ping") // 设置需要生成的表名
                            .addTablePrefix("", ""); // 设置过滤表前缀
                }).injectionConfig((builder) -> {
                    builder.beforeOutputFile((tableInfo, objectMap) -> {
                        System.out.println("tableInfo: " + tableInfo.getEntityName() + "objectMap: " + objectMap.size());
                    });
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
