package com.logging.api.config;

import com.logging.api.strategy.LogExecutionStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean(name="logFormat")
    public String logFormat() {
        return "dasdasd";
    }


    /**
     *  logExecutionStrategy bean 등록시 커스텀한 LogExecutionStrategy 인터페이스 실행
     */
//    @Bean
//    public LogExecutionStrategy logExecutionStrategy() {
//        System.out.println("logExecutionStrategy");
//        return new CustomLogExecutionStrategy();
//    }
}
