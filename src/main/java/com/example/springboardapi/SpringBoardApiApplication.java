package com.example.springboardapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@ServletComponentScan
@SpringBootApplication
public class SpringBoardApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoardApiApplication.class, args);
    }

}
