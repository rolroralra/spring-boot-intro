# 외부 설정 사용 - Environment

## 외부 설정
- 설정 파일
  - application.properties
  - ...
- Environment Variables
- JVM System Properties
- Command Line Arguments

## Spring이 지원하는 다양한 외부 설정 조회 바법
- Environment
- @Value
- @ConfigurationProperties

# Environment - 외부 설정 사용
- `Environment::getProperty(String)` : `String`
- `Environment::getProperty(String, Class<T>)` : `T`

```java
@Configuration
@RequiredArgsConstructor
@Slf4j
public class MyDataSourceEnvironmentConfig {

    private final Environment environment;

    @Bean
    public MyDataSource myDataSource() {
        String url = environment.getProperty("my.datasource.url");
        String username = environment.getProperty("my.datasource.username");
        String password = environment.getProperty("my.datasource.password");
        
        int maxConnection = environment.getProperty("my.datasource.etc.max-connection", Integer.class);
        var timeout = environment.getProperty("my.datasource.etc.timeout", Duration.class);
        var options = environment.getProperty("my.datasource.etc.options", List.class);

        return new MyDataSource(url, username, password, maxConnection, timeout, options);
    }
}
```

```properties
my.datasource.url=local.db.com
my.datasource.username=local_user
my.datasource.password=local_password
my.datasource.etc.max-connection=1
my.datasource.etc.timeout=3500ms
my.datasource.etc.options=CACHE,ADMIN
```

## 참고: Spring에서 제공하는 속성 변환기
[Configuration Properties Type Conversion](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config.typesafe-configuration-properties.conversion)

- `java.time.Duration`
- `java.time.Period`
- `org.springframework.util.unit.DataSize`

# @Value - 외부 설정 사용
- `@Value`에 `${...}` 을 사용해서 외부 설정의 키 값을 주면 원하는 값을 주입 받을 수 있다.
  - `${property-placeholder-style}`
  - `#{SpEL}`
- `@Value` 는 필드, 파라미터 등 사용할 수 있다.
- Default Value 설정도 가능하다.
  - `@Value("${key:default-value}")`

```java
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {
    String value();
}
```

## @Value 사용 예시
```java
@Configuration
@Slf4j
public class MyDataSourceValueConfig {
    @Value("${my.datasource.username}")
    private String url;

    @Value("${my.datasource.username}")
    private String username;

    @Value("${my.datasource.password}")
    private String password;

    @Value("${my.datasource.etc.max-connection:1}")
    private int maxConnection;

    @Value("${my.datasource.etc.timeout:PT10S}")
    private Duration timeout;

    @Value("${my.datasource.etc.options}")
    private List<String> options;

    @Bean
    public MyDataSource myDataSource1() {
        return new MyDataSource(url, username, password, maxConnection, timeout, options);
    }

    @Bean
    public MyDataSource myDataSource2(
        @Value("${my.datasource.username}") String url,
        @Value("${my.datasource.username}") String username,
        @Value("${my.datasource.password}") String password,
        @Value("${my.datasource.etc.max-connection:1}") int maxConnection,
        @Value("${my.datasource.etc.timeout:PT10S}") Duration timeout,
        @Value("${my.datasource.etc.options}") List<String> options
    ) {
        return new MyDataSource(url, username, password, maxConnection, timeout, options);
    }
}
```

# @ConfigurationProperties - 외부 설정 사용
- Spring은 외부 설정의 묶음 정보를 객체로 변환하는 기능을 제공한다.
- **Type-safe Configuration Properties**

```java
@ConfigurationProperties("my.datasource")
public record MyDataSourcePropertiesV2(
    String url,
    String username,
    String password,
    @DefaultValue Etc etc
) {
    public record Etc(
        @DefaultValue("1") int maxConnection,
        @DefaultValue("PT30S") Duration timeout,
        @DefaultValue("DEFAULT") List<String> options
    ) { }
}
```

## @ConfigurationPropertiesScan
- `@ConfigurationProperties`를 하나하나 직접 등록할 때는 `@EnableConfigurationProperties`를 사용한다.
- `@ConfigurationProperties`를 패키지 범위로 자동 등록할 때는 `@ConfigurationPropertiesScan`을 사용한다.

```java
@EnableConfigurationProperties(MyDataSourcePropertiesV2.class)
```
