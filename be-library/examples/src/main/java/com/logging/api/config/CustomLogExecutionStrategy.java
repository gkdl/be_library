package com.logging.api.config;

import com.logging.api.annotation.LogExecutionTime;
import com.logging.api.strategy.LogExecutionStrategy;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

public class CustomLogExecutionStrategy implements LogExecutionStrategy  {
    private static final Logger logger = Logger.getLogger("DefaultLogExecutionStrategy");

    @Override
    public Object execute(ProceedingJoinPoint joinPoint, LogExecutionTime logExecutionTime) throws Throwable {
        logger.info("최고다~~");

        return true;
    }
}
