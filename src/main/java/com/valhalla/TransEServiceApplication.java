package com.valhalla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author Valhalla
 * @description spring boot main
 * @date 2019/10/16
 **/
@SpringBootApplication
public class TransEServiceApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(TransEServiceApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(TransEServiceApplication.class);
    }
}
