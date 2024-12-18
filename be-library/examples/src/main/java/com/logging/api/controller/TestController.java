package com.logging.api.controller;

import com.logging.api.annotation.LogExecutionTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/test/api")
public class TestController {
    @LogExecutionTime(includeArgs = false)
    @PostMapping("/test1")
    public void TestContoller1() {

    }

    @LogExecutionTime
    @PostMapping("/test2")
    public void TestContoller2(@RequestBody Map<String, String> request) {

    }
}
