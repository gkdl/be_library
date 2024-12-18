package com.logging.api.controller;

import com.logging.api.annotation.LogExecutionTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/test/api")
public class TestController {

    /**
     * includeArgs = false 설정시 로그에 파라미터 값 미표시
     */
    @LogExecutionTime(includeArgs = false)
    @PostMapping("/test1")
    public void TestContoller1() {

    }

    /**
     * includeArgs = true 설정시 로그에 파라미터 값 표시
     */
    @LogExecutionTime
    @PostMapping("/test2")
    public void TestContoller2(@RequestBody Map<String, String> request) {

    }
}
