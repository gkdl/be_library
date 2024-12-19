# API 로깅 라이브러리
- API 호출시 실행시간, 종료시간, argument, 등의 정보를 자동 로깅
- gradlew :api-logging:publishAllPublicationsToMavenCentralRepository

## 설치 방법
### build.gralde
```
dependencies {
    implementation group: 'io.github.gkdl', name: 'custom-api-logging', version: '0.0.3'
}
```

### Aop 활성화
```
@EnableAspectJAutoProxy(proxyTargetClass = true)
```

## 사용 방법

### 1. annotation 추가
```JAVA
//arugument 포함하여 log 출력
@LogExecutionTime
@PostMapping("/test1")
public void TestContoller1() {

}
```

### 2. @LogExecutionTime 속성 값
```JAVA
//arugument 속성 log 미출력
@LogExecutionTime(includeArgs = false)
@PostMapping("/test1")
public void TestContoller1() {
    
}
```

### 2. log 포멧 변경
```yaml
#dateFormat : log에 출력되는 date 포멧 변경
#format : log의 전체적인 로그 포멧 변경
logging:
  dateFormat: "yyyy-MM-dd"
  format: "Api 시작 시간 : {startTime}"

```
#### format 변수명
 ```
 startTime : api 호출 시간
 endTime : api 종료시간
 executionTime : api 작업 소요 시간
 method : api 호출 메서드명
 ```

### 3. 커스텀
```JAVA
//LogExecutionStrategy 상속받아 CustomLogExecutionStrategy 구현
public class CustomLogExecutionStrategy implements LogExecutionStrategy  {
    private static final Logger logger = Logger.getLogger("DefaultLogExecutionStrategy");
    
    @Override
    public Object execute(ProceedingJoinPoint joinPoint, LogExecutionTime logExecutionTime) throws Throwable {
        logger.info("출력할 로그 정보 입력");

        return true;
    }
}

// logExecutionStrategy 으로 bean 등록
@Bean
public LogExecutionStrategy logExecutionStrategy() {
    System.out.println("logExecutionStrategy");
    return new CustomLogExecutionStrategy();
}
```

## 라이센스
MIT License

Copyright (c) 2024 Backend Engineer

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
