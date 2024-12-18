package com.logging.api.aspect;

import com.logging.api.annotation.LogExecutionTime;
import com.logging.api.strategy.LogExecutionStrategy;
import org.aspectj.lang.ProceedingJoinPoint;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Logger;

public class DefaultLogExecutionStrategy implements LogExecutionStrategy {
    private static final Logger logger = Logger.getLogger("DefaultLogExecutionStrategy");

    @Override
    public Object execute(ProceedingJoinPoint joinPoint, LogExecutionTime logExecutionTime) throws Throwable {

        // 어노테이션 속성 값 읽기
        boolean includeArgs = logExecutionTime.includeArgs();

        // 메서드 실행 시간 기록
        long startTime = System.nanoTime();

        // 메서드 이름
        String methodName = joinPoint.getSignature().getName();

        SimpleDateFormat sdf = new SimpleDateFormat(DefaultProperties.getDateFormatValue());

        //시작 시간(ms)
        long start = System.currentTimeMillis();

        String startDateTime = sdf.format(new Date(System.currentTimeMillis()));

        // 대상 메소드 실행
        Object proceed = joinPoint.proceed();  // joinPoint.proceed()는 메소드를 실제로 실행합니다.

        String endDateTime = sdf.format(new Date(System.currentTimeMillis()));

        long executionTime = System.currentTimeMillis() - start;  // 실행 시간 계산

        String logMessage = DefaultProperties.getFormatValue()
                .replace("{startTime}", startDateTime)
                .replace("{endTime}", endDateTime)
                .replace("{executionTime}", String.valueOf(executionTime))
                .replace("{method}", String.valueOf(methodName));

        logger.info(logMessage);

        if (includeArgs) {
            Object[] args = joinPoint.getArgs();
            String argsString = args.length > 0
                    ? String.join(", ", Arrays.stream(args).map(Object::toString).toArray(String[]::new))
                    : "No arguments";
            logger.info("Arguments: " + argsString);
        }

        return proceed;  // 실행 결과 반환
    }
}