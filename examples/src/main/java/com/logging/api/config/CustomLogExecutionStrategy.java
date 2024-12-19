package com.logging.api.config;

import com.logging.api.annotation.LogExecutionTime;
import com.logging.api.strategy.LogExecutionStrategy;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

public class CustomLogExecutionStrategy implements LogExecutionStrategy  {
    private static final Logger logger = Logger.getLogger("DefaultLogExecutionStrategy");

    /**
     * 로그 커스텀을 원할 경우 LogExecutionStrategy 인터페이스 구현
     *
     * @param joinPoint
     * @param logExecutionTime
     * @return
     * @throws Throwable  
     */
    @Override
    public Object execute(ProceedingJoinPoint joinPoint, LogExecutionTime logExecutionTime) throws Throwable {
        logger.info("출력할 로그 정보 입력");

        return true;
    }
}
