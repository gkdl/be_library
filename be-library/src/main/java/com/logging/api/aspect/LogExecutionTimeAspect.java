package com.logging.api.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect  // AOP 관련 기능을 처리하는 Aspect 클래스
@Component  // Spring Bean으로 등록
public class LogExecutionTimeAspect {

    // @LogExecutionTime 어노테이션이 붙은 메소드에 대해 AOP 적용
    @Around("@annotation(com.logging.api.annotation.LogExecutionTime)")  // 특정 어노테이션을 기준으로 AOP 적용
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();  // 시작 시간 기록

        // 대상 메소드 실행
        Object proceed = joinPoint.proceed();  // joinPoint.proceed()는 메소드를 실제로 실행합니다.

        long executionTime = System.currentTimeMillis() - start;  // 실행 시간 계산
        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");

        return proceed;  // 실행 결과 반환
    }
}