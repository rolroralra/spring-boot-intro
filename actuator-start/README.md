# Production 준비 기능
> 전투에서 실패한 지휘관은 용서할 수 있지만, 경계에서 실패한 지휘관은 용서할 수 없다.
>
> 프로덕션을 운영에 배포할 때 준비해야 하는 비 기능적 요소

- 지표 `metric`
- 추적 `trace`
- 감사 `auditing`
- 모니터링

# Spring Actuator 시작
```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-actuator'
}
```

## Spring Actuator 동작 확인
```http request
###
GET http://localhost:8080/actuator

###
GET http://localhost:8080/actuator/health
```

<details>
  <summary>http response</summary>
  <p>

```http request
###
GET http://localhost:8080/actuator

HTTP/1.1 200
{
  "_links": {
    "self": {
      "href": "http://localhost:8080/actuator",
      "templated": false
    },
    "health": {
      "href": "http://localhost:8080/actuator/health",
      "templated": false
    },
    "health-path": {
      "href": "http://localhost:8080/actuator/health/{*path}",
      "templated": true
    }
  }
}

###
GET http://localhost:8080/actuator/health

HTTP/1.1 200
{
  "status": "UP"
}
```

  </p>
</details>

## Actuator 기능을 WEB에 노출
```yaml
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

- Acutoator가 제공하는 수 많은 기능을 확인할 수 있다.
  - `self`
  - `beans` : Spring Container에 등록된 Spring Bean 정보
  - `caches`
  - `health` : Application Health 정보
  - `info` : Application 정보
  - `conditions` : `Condition`을 통해서 Bean을 등록할 때 평가 조건 관련 정보
  - `configprops` : `@ConfigurationProperties` 정보
  - `env` : `Environment` 정보
  - `loggers` : Application Logger 설정을 보여주고 변경도 할 수 있다.
  - `heapdump`
  - `threaddump`
  - `metrics` : Application의 metric 정보
  - `scheduledtasks`
  - `mappings` : `@RequestMapping` 정보
  - `shutdown` : Application 종료한다. 이 기능은 기본적으로 비활성화 되어 있다.
- Actuator가 제공하는 기능 하나 하나를 `Endpoint`라 한다.
  - `/actuator/{endpoint}`로 접근할 수 있다.
- [Actuator Endpoints - Spring 공식 메뉴얼](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.endpoints)

<details>
  <summary>http response</summary>
  <p>

```http request
###
GET http://localhost:8080/actuator

HTTP/1.1 200 
{
  "_links": {
    "self": {
      "href": "http://localhost:8080/actuator",
      "templated": false
    },
    "beans": {
      "href": "http://localhost:8080/actuator/beans",
      "templated": false
    },
    "caches-cache": {
      "href": "http://localhost:8080/actuator/caches/{cache}",
      "templated": true
    },
    "caches": {
      "href": "http://localhost:8080/actuator/caches",
      "templated": false
    },
    "health-path": {
      "href": "http://localhost:8080/actuator/health/{*path}",
      "templated": true
    },
    "health": {
      "href": "http://localhost:8080/actuator/health",
      "templated": false
    },
    "info": {
      "href": "http://localhost:8080/actuator/info",
      "templated": false
    },
    "conditions": {
      "href": "http://localhost:8080/actuator/conditions",
      "templated": false
    },
    "configprops-prefix": {
      "href": "http://localhost:8080/actuator/configprops/{prefix}",
      "templated": true
    },
    "configprops": {
      "href": "http://localhost:8080/actuator/configprops",
      "templated": false
    },
    "env": {
      "href": "http://localhost:8080/actuator/env",
      "templated": false
    },
    "env-toMatch": {
      "href": "http://localhost:8080/actuator/env/{toMatch}",
      "templated": true
    },
    "loggers": {
      "href": "http://localhost:8080/actuator/loggers",
      "templated": false
    },
    "loggers-name": {
      "href": "http://localhost:8080/actuator/loggers/{name}",
      "templated": true
    },
    "heapdump": {
      "href": "http://localhost:8080/actuator/heapdump",
      "templated": false
    },
    "threaddump": {
      "href": "http://localhost:8080/actuator/threaddump",
      "templated": false
    },
    "metrics-requiredMetricName": {
      "href": "http://localhost:8080/actuator/metrics/{requiredMetricName}",
      "templated": true
    },
    "metrics": {
      "href": "http://localhost:8080/actuator/metrics",
      "templated": false
    },
    "scheduledtasks": {
      "href": "http://localhost:8080/actuator/scheduledtasks",
      "templated": false
    },
    "mappings": {
      "href": "http://localhost:8080/actuator/mappings",
      "templated": false
    }
  }
}
```

  </p>
</details>


# Actuator Endpoint 설정
> Endpoint 활성화 + Endpoint 노출 설정 둘다 적용되어야 사용할 수 있다. 
> 
> [Enabling Endpoints - Spring 공식 메뉴얼](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.endpoints.enabling)

1. Endpoint 활성화 : `management.endpoint.{endpoint}.enabled=true`
2. Endpoint 노출 설정 : `management.endpoints.web.exposure.include="*"`

## Endpoint 활성화
```yaml
management:
  endpoint:
    shutdown:
      enabled: true
```
- `shutdown` Endpoint는 기본적으로 비활성화 되어 있다.

## Endpoint 노출 설정
```yaml
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

# Health Endpoint
> Application의 Health 정보를 제공하는 Endpoint
> 
> [Health Information - Spring 공식 메뉴얼](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.endpoints.health)

- [Actuator가 기본적으로 제공하는 HealthIndicator 목록 - Spring 공식 메뉴얼](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.endpoints.health.auto-configured-health-indicators)
- [Custom HealthIndicator 구현 방법 - Spring 공식 메뉴얼](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.endpoints.health.writing-custom-health-indicators)
  ```java
  @Component
  public class MyHealthIndicator implements HealthIndicator {
  
      @Override
      public Health health() {
          int errorCode = check();
          if (errorCode != 0) {
              return Health.down().withDetail("Error Code", errorCode).build();
          }
          return Health.up().build();
      }
  
      private int check() {
          // perform some specific health check
          return ...
      }
  
  }
  ```

## Health Endpoint 옵션 설정
- `management.endpoint.health.show-details` : Health 정보를 더 자세히 보는 옵션
- `management.endpoint.health.show-components` : Health 정보를 Component 단위로 보는 옵션

```yaml
management:
  endpoint:
    health:
#      show-details: always
      show-components: always
```

# Info Endpoint
> Application의 정보를 제공하는 Endpoint
> 
> `env`, `java`, `os` 기본적으로 비활성화 되어 있다.
> 
> [Application Information - Spring 공식 메뉴얼](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.endpoints.info)



- `java` : Java Runtime 정보
- `os` : OS 정보
- `env` : `Environment` 에서 `info.`으로 시작하는 정보
- `build` : 빌드 정보, `META-INF/build-info.properties` 파일이 필요하다.
- `git` : Git 정보, `git.properties` 파일이 필요하다.

```yaml
management:
  info:
    java:
      enabled: true
    os:
      enabled: true
```

```http request
GET http://localhost:8080/actuator/info

HTTP/1.1 200 
{
  "java": {
    "version": "17.0.8",
    "vendor": {
      "name": "Azul Systems, Inc.",
      "version": "Zulu17.44+15-CA"
    },
    "runtime": {
      "name": "OpenJDK Runtime Environment",
      "version": "17.0.8+7-LTS"
    },
    "jvm": {
      "name": "OpenJDK 64-Bit Server VM",
      "vendor": "Azul Systems, Inc.",
      "version": "17.0.8+7-LTS"
    }
  },
  "os": {
    "name": "Mac OS X",
    "version": "14.0",
    "arch": "aarch64"
  }
}
```

## env
- application.properties 에서 `info`로 시작하는 부분의 정보가 노출된다.

```yaml
management:
  info:
    env:
      enabled: true

info:
  app:
    name: hello-actuator
    company: www.abc.com
```

```http request
GET http://localhost:8080/actuator/info

HTTP/1.1 200 
{
  "app": {
    "name": "hello-actuator",
    "company": "www.abc.com"
  },
  ...
}
```

## build
- `META-INF/build-info.properties` 파일이 필요하다.
- build, git의 경우 기본적으로 활성화 되어 있다.

```properties
build.artifact=actuator
build.group=hello
build.name=actuator
build.time=2023-01-01T00:00:00.000+000000Z
build.version=0.0.1-SNAPSHOT
```

```http request
GET http://localhost:8080/actuator/info

{
  ...,
  "build": {
    "artifact": "actuator",
    "name": "actuator",
    "time": "2023-01-01T00:00:00.000+000000Z",
    "version": "0.0.1-SNAPSHOT",
    "group": "hello"
  },
  ...
}
```

## git
- `git.properties` 파일이 필요하다.
- `com.gorylenko.gradle-git-properties` gradle plugin 을 적용해야 한다.
- 프로젝트가 git으로 관리되고 있어야 한다.

```groovy
plugins {
  id "com.gorylenko.gradle-git-properties" version "2.4.1"
}
```

- gradle build 후 `build/resources/main/git.properties` 파일이 생성된다.

```properties
git.branch=main
git.build.host=shinyoungui-MacBookPro.local
git.build.user.email=shinyoung@socar.kr
git.build.user.name=socar-shinyoung
git.build.version=0.0.1-SNAPSHOT
git.closest.tag.commit.count=
git.closest.tag.name=
git.commit.id=da2b43203c1977458e7595702e9df1153fc03b50
git.commit.id.abbrev=da2b432
git.commit.id.describe=
git.commit.message.full=docs\: @Profile\n
git.commit.message.short=docs\: @Profile
git.commit.time=2023-10-01T23\:47\:57+0900
git.commit.user.email=shinyoung@socar.kr
git.commit.user.name=socar-shinyoung
git.dirty=true
git.remote.origin.url=git@github.com\:/rolroralra/spring-boot-intro.git
git.tags=
git.total.commit.count=10
```

```http request
GET http://localhost:8080/actuator/info

HTTP/1.1 200
{
  ...,
  "git": {
    "branch": "main",
    "commit": {
      "id": "da2b432",
      "time": "2023-10-01T14:47:57Z"
    }
  },
  ...
}
```

- git에 대한 더 자세한 정보를 보고 싶다면, 다음 옵션을 적용하면 된다.
  - `management.info.git.mode=full`

```yaml
management:
  info:
    git:
      mode: full # default: simple
```

## info Endpoint Custom 설정 
- [Writing Custom InfoContributors - Spring 공식 메뉴얼](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.endpoints.info.writing-custom-info-contributors)

```java
@Component
public class MyInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("example", Collections.singletonMap("key", "value"));
    }
}
```

```json
{
    "example": {
        "key" : "value"
    }
}
```

# Loggers Endpoint
> Application의 Logger 설정을 보여주고 변경할 수 있는 Endpoint
> 
> [Loggers - Spring 공식 메뉴얼](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.loggers)

- `GET /actuator/loggers` : 모든 Logger 설정을 보여준다.
- `GET /actuator/loggers/{logger-name}` : 특정 Logger 설정을 보여준다.
- `POST /actuator/loggers/{logger-name}` : 특정 Logger 설정을 변경한다.
  ```json
  { "configuredLevel": "TRACE" }
  ```
  
# HttpExchanges - HTTP 요청 응답 기록
> HTTP 요청 응답의 과거 기록을 확인할 수 있는 Endpoint

- `HttpExchangeRepository` 인터페이스의 구현체를 Bean으로 등록해야 한다.
  - 구현체를 Bean으로 등록하지 않으면, `httpexchanges` Endpoint는 활성화되지 않는다.
  - `InMemoryHttpExchangeRepository` : 기본적으로 제공하는 구현체
    - 최대 100개의 HTTP 요청을 저장한다.
    - `InMemoryHttpExchangeRepository::setCapacity` 로 최대 저장하는 요청수를 변경할 수 있다.

```java
public interface HttpExchangeRepository {

	List<HttpExchange> findAll();

	void add(HttpExchange httpExchange);
}
```

```java
public final class HttpExchange {

    private final Instant timestamp;

    private final Request request;

    private final Response response;

    private final Principal principal;

    private final Session session;

    private final Duration timeTaken;
}
```

# Actuator 와 보안
- Actuator Endpoint 들은 외부 인터넷에서 접근이 불가능하게 막아야 한다.
- 내부에서만 접근 가능한 내부망을 사용하는 것이 안전하다.

## Actuator 포트 설정
- `management.server.port` 설정값으로 변경 가능
```yaml
management:
  server:
    port: 9292
```

## Actuator 인증 설정
- `/actuator` 경로에 Servlet Filter, Spring Interceptor, Spring Security를 통해서 인증 처리 개발이 필요하다.

## Actuator 경로 변경
- `management.endpoints.web.base-path` 설정값으로 변경 가능
```yaml
management:
  endpoints:
    web:
      base-path: /manage
```
