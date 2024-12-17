package com.logging.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.logging.api.aspect")
public class LoggingConfig {
    public LoggingConfig() {
        System.out.println("###################");
    }
}
