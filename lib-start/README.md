# SpringBoot 라이브러리 버전 관리
- `org.springframework.boot`
- `io.spring.dependency-management` 
- gradle plugin을 사용하면 SpringBoot에서 관리하는 외부 라이브러리의 버전을 명시하지 않아도 된다.


```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.1.4'
    id 'io.spring.dependency-management' version '1.1.3'
}
```

## 참고 - BOM
- Bill of materials
- [io.spring.dependency-management - BOM](https://github.com/spring-projects/spring-boot/blob/main/spring-boot-project/spring-boot-dependencies/build.gradle)

## 참고 - SpringBoot가  관리하는 외부 라이브러리 버전
- [SpringBoot Managed Dependency Coordinates](https://docs.spring.io/spring-boot/docs/current/reference/html/dependency-versions.html#appendix.dependency-versions.coordinates)
- SpringBoot가 관리하지 않는 외부 라이브러리의 경우, 직접 입력해야한다.

# SpringBoot Starter
> `spring-boot-starter-*` 

- `spring-boot-starter` : 핵심 스타터, 자동 구성, 로깅, yaml
- `spring-boot-starter-jdbc` : JDBC, HikariCP 커넥션 풀
- `spring-boot-starter-data-jpa` : 스프링 데이터 JPA, 하이버네이트
- `spring-boot-starter-mongodb` : 스프링 데이터 몽고
- `spring-boot-starter-redis` : 스프링 데이터 Redis, Lettuce 클라이언트
- `spring-boot-starter-thymeleaf` : Tyhmeleaf 뷰와 웹 MVC
- `spring-boot-starter-web` : 웹 구축을 위한 스타터, RESTful, SpringMVC, 내장 톰캣
- `spring-boot-starter-validation` : Java Bean 검증기 (Hibernate Validator)
- `spring-boot-starter-batch` : 스프링 배치를 위한 스타터

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
}
```

## 외부 라이브러리 버전 변경
```groovy
ext['tomcat.version'] = '10.1.4'
```
```groovy
ext {
    set('tomcat.version', '10.1.4')
}
```

### 외부 라이브러리 버전 변경시, 필요한 속성값
- [외부 라이브러리 버전 변경시 필요한 속성 값](https://docs.spring.io/spring-boot/docs/current/reference/html/dependency-versions.html#appendix.dependency-versions.properties)

## 참고 - SpringBoot Starter 전체 목록
[SpringBoot Stater 목록](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.build-systems.starters)


