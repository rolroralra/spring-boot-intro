# Spring AutoConfiguration 살펴보기

## JdbcTemplate AutoConfiguration
```java
@AutoConfiguration(after = DataSourceAutoConfiguration.class)
@ConditionalOnClass({ DataSource.class, JdbcTemplate.class })
@ConditionalOnSingleCandidate(DataSource.class)
@EnableConfigurationProperties(JdbcProperties.class)
@Import({ DatabaseInitializationDependencyConfigurer.class, JdbcTemplateConfiguration.class,
		NamedParameterJdbcTemplateConfiguration.class })
public class JdbcTemplateAutoConfiguration {

}
```
- 자동 구성을 사용하려면 `@AutoConfiguration`을 사용해야 한다.
- `@ConditionalOnClass`
  - 클래스패스에 해당 클래스가 존재할 때만 자동 구성을 수행한다.
- `@Import`
  - `@Configuration` 클래스를 가져온다.

## Spring AutoConfiguration 예시 목록
- `JdbcTemplateAutoConfiguration` : `JdbcTemplate`을 자동 구성한다.
- `DataSourceAutoConfiguration` : `DataSource`를 자동 구성한다.
- `DataSourceTransactionManagerAutoConfiguration` : `TransactionManager`를 자동 구성한다.

## SpringBoot 가 제공하는 AutoConfiguration
- [SpringBoot가 제공하는 AutoConfiguration 목록](https://docs.spring.io/spring-boot/docs/current/reference/html/auto-configuration-classes.html#appendix.auto-configuration-classes.core)
- `spring-boot-autoconfigure` 라이브러리가 제공한다.
  - base package: `org.springframework.boot.autoconfigure`

# @Conditional
> 조건에 따라 빈을 등록하거나 등록하지 않는다.

- `Condition` 인터페이스를 구현해야 한다.

## Condition
```java
@FunctionalInterface
public interface Condition {

	boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata);
}
```

- `ConditionContext` : Spring Container, 환경 정보 등을 담고 있다.
- `AnnotatedTypeMetadata` : Annotation 메타 정보를 담고 있다.

## @ConditionalOnProperty
```java
@Conditional(OnPropertyCondition.class)
public @interface ConditionalOnProperty {
    // ...
}
```

## @ConditionalOnXxx
> Spring은 `@Conditional`과 관련해서 개발자가 편리하게 사용할 수 있도록 수많은 `@ConditionalOnXxx`를 제공한다.

[@ConditionalOnXxx 공식 메뉴얼](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.developing-auto-configuration.condition-annotations)

- `@ConditionalOnClass`, `@ConditionalOnMissingClass`
  - 클래스가 존재하는지 여부로 조건을 판단한다.
- `@ConditionalOnBean`, `@ConditionalOnMissingBean`
  - 빈이 존재하는지 여부로 조건을 판단한다.
- `@ConditionalOnProperty`
  - 특정 프로퍼티가 존재하는지 여부로 조건을 판단한다.
- `@ConditionalOnResource`
  - 특정 리소스가 존재하는지 여부로 조건을 판단한다.
- `@ConditionalOnWebApplication`, `@ConditionalOnNotWebApplication`
  - 웹 어플리케이션인지 여부로 조건을 판단한다.
- `@ConditionalOnExpression`
  - SpEL 표현식으로 조건을 판단한다.
