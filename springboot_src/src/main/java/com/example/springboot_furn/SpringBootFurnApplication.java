package com.example.springboot_furn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.example.springboot_furn.mapper")
@SpringBootApplication
public class SpringBootFurnApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFurnApplication.class, args);
    }

}
