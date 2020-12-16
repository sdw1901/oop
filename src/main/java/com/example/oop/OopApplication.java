package com.example.oop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.oop.mapper")
public class OopApplication {

    public static void main(String[] args) {
        SpringApplication.run(OopApplication.class, args);
    }

}
