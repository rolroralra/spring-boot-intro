# SpringBoot 와 웹 서버
- Spring Container를 생성한다.
- 내장 톰캣을 생성한다.

```java
@SpringBootApplication
public class BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}
}
```

## ServletWebServerApplicationContextFactory
> SpringBoot 에서 스프링 컨테이너를 생성하는 코드

```java
class ServletWebServerApplicationContextFactory implements ApplicationContextFactory {
    // ...
    
    private ConfigurableApplicationContext createContext() {
        if (!AotDetector.useGeneratedArtifacts()) {
            return new AnnotationConfigServletWebServerApplicationContext();
        }
        return new ServletWebServerApplicationContext();
    }
}
```

## TomcatServletWebServerFactory
> SpringBoot 내부에서 내장 톰캣을 생성하는 코드

```java
public class TomcatServletWebServerFactory extends AbstractServletWebServerFactory
		implements ConfigurableTomcatWebServerFactory, ResourceLoaderAware {
    @Override
    public WebServer getWebServer(ServletContextInitializer... initializers) {
        Tomcat tomcat = new Tomcat();
        // ...
        
        Connector connector = new Connector(this.protocol);
        // ...
        
        return getTomcatWebServer(tomcat);
    }
}
```

## SpringBoot Jar 파일 구성
- `META-INF`
  - `MANIFEST.MF` 
- `BOOT-INF`
  - `classes` : 우리가 개발한 class 파일과 resource 파일
  - `lib` : 외부 라이브러리
  - `classpath.idx` : 외부 라이브러리 경로
  - `layers.idx` : SpringBoot 구조 경로
- `org.springframework.boot.loader`
  - `JarLauncher.class` : SpringBoot main() 실행 클래스

### 실행 가능 Jar
- SpringBoot에서 새롭게 정의한 것
- jar 내부에 jar를 포함할 수 있는 특별한 구조

### MANIFEST.MF
> `Main-Class` 를 제외한 나머지 필드는 Java 표준이 아니다.
> 스프링 부트가 임의로 사용하는 정보이다. 

- `Start-Class`
  - SpringBoot의 main() 실행 클래스
- `Spring-Boot-Version`
  - SpringBoot 버전
- `Spring-Boot-Classes`
  - 개발한 클래스 경로
- `Spring-Boot-Lib`
  - 외부 라이브러리 경로
- `Spring-Boot-Classpath-Index`
  - 외부 라이브러리 목록
- `Spring-Boot-Layers-Index`
  - SpringBoot 구조 정보

```manifest
Manifest-Version: 1.0
Main-Class: org.springframework.boot.loader.JarLauncher
Start-Class: hello.boot.BootApplication
Spring-Boot-Version: 3.0.2
Spring-Boot-Classes: BOOT-INF/classes/
Spring-Boot-Lib: BOOT-INF/lib/
Spring-Boot-Classpath-Index: BOOT-INF/classpath.idx
Spring-Boot-Layers-Index: BOOT-INF/layers.idx
Build-Jdk-Spec: 17
```

### classpath.idx
``` yaml
- "BOOT-INF/lib/lombok-1.18.28.jar"
- "BOOT-INF/lib/spring-webmvc-6.0.4.jar"
- "BOOT-INF/lib/spring-web-6.0.4.jar"
...
```

### layers.idx
``` yaml
- "dependencies":
  - "BOOT-INF/lib/"
- "spring-boot-loader":
  - "org/"
- "snapshot-dependencies":
- "application":
  - "BOOT-INF/classes/"
  - "BOOT-INF/classpath.idx"
  - "BOOT-INF/layers.idx"
  - "META-INF/"
```


