package com.logging.api.strategy;

import com.logging.api.annotation.LogExecutionTime;
import org.aspectj.lang.ProceedingJoinPoint;

public interface LogExecutionStrategy {
    Object execute(ProceedingJoinPoint joinPoint, LogExecutionTime logExecutionTime) throws Throwable;
}
