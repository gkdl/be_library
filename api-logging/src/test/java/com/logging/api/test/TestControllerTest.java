package com.logging.api.test;

import com.logging.api.config.LoggingConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@EnableAspectJAutoProxy
@ContextConfiguration(classes = {TestControllerTest.TestConfig.class, LoggingConfig.class})  // 설정 클래스 지정
@AutoConfigureMockMvc
public class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;  // HTTP 요청을 테스트할 MockMvc

    @Test
    public void testTestController() throws Exception {
        // '/test' 경로로 GET 요청을 보내고 응답을 확인
        mockMvc.perform(MockMvcRequestBuilders.get("/test"))
                .andExpect(MockMvcResultMatchers.status().isOk())  // 200 OK 상태 확인
                .andExpect(MockMvcResultMatchers.content().string("Test method executed"));  // 응답 내용 확인
    }

    @Test
    public void testTestController2() throws Exception {
        // '/test' 경로로 GET 요청을 보내고 응답을 확인
        mockMvc.perform(MockMvcRequestBuilders.get("/test2"))
                .andExpect(MockMvcResultMatchers.status().isOk())  // 200 OK 상태 확인
                .andExpect(MockMvcResultMatchers.content().string("Test method executed"));  // 응답 내용 확인
    }

    @Configuration
    static class TestConfig {
        @Bean
        public TestController testController() {
            return new TestController();
        }
    }
}