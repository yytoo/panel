package com.template.manual.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.apache.ibatis.type.JdbcType;

import java.nio.file.Paths;
import java.util.Collections;

public class CodeGeneratorUtil {
    /**
     * 自定义模板放在src/main/resources/templates下面
     * @param args
     */
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/sakila";
        System.out.println(System.getProperty("user.dir"));
        // 使用 FastAutoGenerator.create() 方法创建生成器实例
        FastAutoGenerator.create(url, "root", "123456")
                .globalConfig(builder -> {
                    builder.author("Y") // 设置作者
                            .outputDir(Paths.get(System.getProperty("user.dir")) + "/manual/src/main/java")// 设置输出目录
                            .commentDate("yyyy-MM-dd")
                            .disableOpenDir(); // 禁用打开输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.template") // 设置父包名
                            .moduleName("manual") // 设置模块名
                            .entity("pojo")
                            .mapper("mapper")
                            .service("service")
                            .serviceImpl("service.impl")
                            .pathInfo(Collections.singletonMap(OutputFile.xml,System.getProperty("user.dir") + "/manual/src/main/resources/mapper"));	//设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_user") // 设置需要生成的表名
                            .addTablePrefix("t_", "sys_"); // 设置过滤表前缀
                    builder.entityBuilder()
                            .enableFileOverride() //覆盖已生成文件
                            .enableLombok()
                            .enableTableFieldAnnotation(); //添加列的对应关系@TableField
                    builder.mapperBuilder()
                            .enableFileOverride();
                    builder.serviceBuilder()
                            .enableFileOverride();
                    builder.controllerBuilder()
                            .enableFileOverride()
                            .enableRestStyle()// 开启 REST 风格控制器
                            .enableHyphenStyle(); // 开启驼峰转连字符;
                })
                .templateEngine(new VelocityTemplateEngine())  // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute(); // 执行代码生成
    }
}