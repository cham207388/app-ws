package com.udemy.appws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AppWsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppWsApplication.class, args);
    }
}
