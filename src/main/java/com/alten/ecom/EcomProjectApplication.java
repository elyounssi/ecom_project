package com.alten.ecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EcomProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcomProjectApplication.class, args);
    }

}
