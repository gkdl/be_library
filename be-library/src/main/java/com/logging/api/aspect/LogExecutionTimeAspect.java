package com.logging.api.aspect;

import com.logging.api.annotation.LogExecutionTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect  // AOP 관련 기능을 처리하는 Aspect 클래스
@Component  // Spring Bean으로 등록
public class LogExecutionTimeAspect {
    private static final Logger logger = Logger.getLogger(LogExecutionTimeAspect.class.getName());

    // @LogExecutionTime 어노테이션이 붙은 메소드에 대해 AOP 적용
    @Around("@annotation(logExecutionTime)")  // 특정 어노테이션을 기준으로 AOP 적용
    public Object logExecutionTime(ProceedingJoinPoint joinPoint, LogExecutionTime logExecutionTime) throws Throwable {

        // 어노테이션 속성 값 읽기
        boolean includeArgs = logExecutionTime.includeArgs();

        // 메서드 실행 시간 기록
        long startTime = System.nanoTime();

        // 메서드 이름
        String methodName = joinPoint.getSignature().getName();

        long start = System.currentTimeMillis();  // 시작 시간 기록
        logger.info("API 호출 시작: " + joinPoint.getSignature() + " | 프록시 객체 : " + joinPoint.getThis());
        // 대상 메소드 실행
        Object proceed = joinPoint.proceed();  // joinPoint.proceed()는 메소드를 실제로 실행합니다.

        if(includeArgs) {
            // 메서드 인자들
            Object[] args = joinPoint.getArgs();
            String argsString = Arrays.stream(args)
                    .map(Object::toString)
                    .reduce((arg1, arg2) -> arg1 + ", " + arg2)
                    .orElse("");

            System.out.println("Arguments: " + argsString);

        }

        long executionTime = System.currentTimeMillis() - start;  // 실행 시간 계산
        logger.info("API 호출 종료: " + joinPoint.getSignature() + " | 호출 시간: " + (executionTime) + "ms");

        return proceed;  // 실행 결과 반환
    }
}