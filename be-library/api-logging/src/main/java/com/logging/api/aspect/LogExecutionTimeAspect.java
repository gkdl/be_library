package com.logging.api.aspect;

import com.logging.api.annotation.LogExecutionTime;
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
@ConditionalOnBean(name = {"logFormat"})
public abstract  class LogExecutionTimeAspect {
    private static final Logger logger = Logger.getLogger(LogExecutionTimeAspect.class.getName());

    private final String logFormat;

    @Autowired
    public LogExecutionTimeAspect(@Qualifier("logFormat") String logFormat) {
        this.logFormat = StringUtils.hasText(logFormat)
                ? logFormat
                : "Api 호출 시작 : {startSignature} | Api 호출 종료 : {endSignature} | 호출 시간: {executionTime} ms"; // 기본값 설정
    }
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

        //시작 시간
        String startSignature = joinPoint.getSignature().toString();

        // 대상 메소드 실행
        Object proceed = joinPoint.proceed();  // joinPoint.proceed()는 메소드를 실제로 실행합니다.

        //종료 시간
        String endSignature = joinPoint.getSignature().toString();

        long executionTime = System.currentTimeMillis() - start;  // 실행 시간 계산

        String logMessage = logFormat
                .replace("{startSignature}", startSignature)
                .replace("{endSignature}", endSignature)
                .replace("{executionTime}", String.valueOf(executionTime));

        logger.info(logMessage);

        // 로그 메시지 생성 (커스터마이징 메서드 호출)
        String logCustomMessage = generateLogMessage(joinPoint);

        logger.info(logCustomMessage);

        return proceed;  // 실행 결과 반환
    }

    //사용자가 남기고 싶은 로그를 추가로 남길수 있는 커스터 마이징 메서드
    protected abstract String generateLogMessage(ProceedingJoinPoint joinPoint);
}