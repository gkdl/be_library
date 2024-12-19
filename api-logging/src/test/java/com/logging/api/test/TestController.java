package com.logging.api.test;


import com.logging.api.annotation.LogExecutionTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @LogExecutionTime  // 메서드 실행 시간을 로깅
    @GetMapping("/test")  // '/test' 경로로 GET 요청 처리
    public String testMethod() throws InterruptedException {
        System.out.println("testMethod");
        // 500ms 지연을 주어 실행 시간을 확인할 수 있도록 함
        Thread.sleep(500);  // 500ms 지연
        return "Test method executed";  // 응답
    }

    @LogExecutionTime(includeArgs = false)  // 메서드 실행 시간을 로깅
    @GetMapping("/test2")  // '/test' 경로로 GET 요청 처리
    public String testMethod2() throws InterruptedException {
        System.out.println("testMethod");
        // 500ms 지연을 주어 실행 시간을 확인할 수 있도록 함
        Thread.sleep(500);  // 500ms 지연
        return "Test method executed";  // 응답
    }
}