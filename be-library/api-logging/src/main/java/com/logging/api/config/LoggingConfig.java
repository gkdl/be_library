package com.logging.api.config;

import com.logging.api.aspect.DefaultLogExecutionStrategy;
import com.logging.api.strategy.LogExecutionStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.logging.api.aspect")
public class LoggingConfig {

    @Bean(name = "logExecutionStrategy")
    @ConditionalOnMissingBean(name = "logExecutionStrategy")
    public LogExecutionStrategy logExecutionStrategy() {
        return new DefaultLogExecutionStrategy();
    }
}
