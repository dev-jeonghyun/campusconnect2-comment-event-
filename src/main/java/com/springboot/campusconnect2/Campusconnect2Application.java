package com.springboot.campusconnect2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // JPA Auditing 활성화
public class Campusconnect2Application {
    public static void main(String[] args) {
        SpringApplication.run(Campusconnect2Application.class, args);
    }
}
