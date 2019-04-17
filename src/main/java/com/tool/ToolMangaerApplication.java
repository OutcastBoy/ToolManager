package com.tool;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScans;


//@SpringBootApplication
@SpringBootApplication(scanBasePackages = "com.tool.tomcat")
@MapperScan(basePackages = "com.tool.tomcat.monitor.dao")
@EnableAutoConfiguration
public class ToolMangaerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolMangaerApplication.class, args);
    }

}
