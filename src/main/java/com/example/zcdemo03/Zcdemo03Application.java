package com.example.zcdemo03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
public class Zcdemo03Application extends SpringBootServletInitializer {

    /**
     * 从外部启动tomcat要
     * 继承SpringBootServletInitializer类，重写configure方法
     * */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(Zcdemo03Application.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(Zcdemo03Application.class, args);
    }

}
