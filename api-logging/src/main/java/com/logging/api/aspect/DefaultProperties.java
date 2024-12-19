package com.logging.api.aspect;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DefaultProperties {

    // static 변수
    private static String formatValue;
    private static String dateFormatValue;

    // 인스턴스 변수로 주입받기
    @Value("${logging.format}")
    private String format;

    @Value("${logging.dateFormat}")
    private String dateFormat;

    @PostConstruct
    public void init() {
        // static 변수에 값 할당
        formatValue = format;
        dateFormatValue = dateFormat;
    }

    public static String getDateFormatValue() {
        return dateFormatValue;
    }

    public static String getFormatValue() {
        return formatValue;
    }
}
