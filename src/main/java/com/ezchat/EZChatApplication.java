package com.ezchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(
        scanBasePackages = {"com.ezchat"},
        exclude = DataSourceAutoConfiguration.class
)
public class EZChatApplication {
    public static void main(String[] args) {
        SpringApplication.run(EZChatApplication.class, args);
    }
}
