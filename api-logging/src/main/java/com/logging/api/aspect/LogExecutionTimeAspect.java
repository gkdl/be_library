package com.logging.api.aspect;

import com.logging.api.annotation.LogExecutionTime;
import com.logging.api.strategy.LogExecutionStrategy;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.logging.Logger;

@Aspect  // AOP 관련 기능을 처리하는 Aspect 클래스
@Component  // Spring Bean으로 등록
public  class LogExecutionTimeAspect {

    private final LogExecutionStrategy logExecutionStrategy;

    @Autowired
    public LogExecutionTimeAspect(LogExecutionStrategy logExecutionStrategy) {
        this.logExecutionStrategy = logExecutionStrategy;
    }

    @Around("@annotation(logExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint, LogExecutionTime logExecutionTime) throws Throwable {
        return logExecutionStrategy.execute(joinPoint,logExecutionTime);
    }
}