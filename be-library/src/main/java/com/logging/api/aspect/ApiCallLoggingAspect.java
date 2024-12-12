package com.logging.api.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component  // 이 어노테이션을 통해 AOP가 적용됩니다.
public class ApiCallLoggingAspect {

    private static final Logger logger = Logger.getLogger(ApiCallLoggingAspect.class.getName());

    @Around("execution(* *(..))")  // 모든 메서드에 대해 AOP 적용
    public Object logApiCall(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        logger.info("API 호출 시작: " + joinPoint.getSignature());

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        logger.info("API 호출 종료: " + joinPoint.getSignature() + " | 호출 시간: " + (endTime - startTime) + "ms");

        return result;
    }
}