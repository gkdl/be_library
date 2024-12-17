package com.logging.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean(name="logFormat")
    public String logFormat() {
        return "dasdasd";
    }
}
