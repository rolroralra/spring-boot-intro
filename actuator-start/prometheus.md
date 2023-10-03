# Prometheus
![prometheus-grafana-architecture.png](docs/prometheus-grafana-architecture.png)

![prometheus-architecture.png](docs/prometheus-architecture.png)

## Prometheus  Applicaiton 설정
1. Application 설정
2. Prometheus 설정

### 1. Application 설정
- Prometheus가 Application의 Metric을 가져갈수 있도록, Application에서 Prometheus Metric 포맷에 맞추어 Metric을 제공해야한다.
- `io.micrometer:micrometer-registry-prometheus` 의존성을 추가하면, Spring Boot Actuator가 제공하는 Metric을 Prometheus Metric 포맷으로 제공한다.
  - `/actuator/promethues` Endpoint가 자동으로 추가된다.

```groovy
dependencies {
    implementation 'io.micrometer:micrometer-registry-prometheus'
}
```

### Prometheus 정상 작동 확인
![prometheus-check.png](docs/prometheus-check.png)

### 2. Prometheus 설정
- Prometheus가 Application의 Metric을 가져갈수 있도록, Prometheus 설정에 Application을 등록해야한다.
- `prometheus.yml` 설정 파일에 Application을 등록한다.

```yaml
# 추가
scrape_configs:
  - job_name: "spring-actuator"
    metrics_path: '/actuator/prometheus'
    scrape_interval: 1m
    static_configs:
    - targets:
      - 'localhost:8080'
```

## Prometheus 기본 기능
- [Prometheus Querying Basic - Prometheus 공식 메뉴얼](https://prometheus.io/docs/prometheus/latest/querying/basics/)

- [Prometheus Querying Operators - Prometheus 공식 메뉴얼](https://prometheus.io/docs/prometheus/latest/querying/operators/)

- [Prometheus Querying Functions - Prometheus 공식 메뉴얼](https://prometheus.io/docs/prometheus/latest/querying/functions/)
### Filter
> label 기준으로 필터를 사용할 수 있다.

- `=` : label 값이 일치하는 경우
- `!=` : label 값이 일치하지 않는 경우
- `=~` : label 값이 정규식과 일치하는 경우
- `!~` : label 값이 정규식과 일치하지 않는 경우

#### Filter 예시
- `http_server_requests_seconds_count{uri="/actuator/prometheus"}`
- `http_server_requests_seconds_count{uri!="/actuator/prometheus", status="200"}`
- `http_server_requests_seconds_count{method=~"GET|POST"}`
- `http_server_requests_seconds_count{uri!~"/actuator.*"}`

### 연산자
- 아래와 같은 연산자를 지원한다.
  - `+`
  - `-`
  - `*`
  - `/`
  - `%`
  - `^`

### sum()
- 값의 합계를 구한다.
  - `sum(http_server_requests_seconds_count{outcome="SUCCESS"})`

### sum by()()
- SQL의 group by 기능과 유사하다.
  - `sum by (method, status) (http_server_requests_seconds_count)`

### count()
- Metric 자체의 수를 구한다.
  - `count(http_server_requests_seconds_count)`

### topk(k, ...)
- 상위 3개 Metric 조회
  - `topk(3, http_server_requests_seconds_count)`

### offset
- 현재를 기준으로 특정 과거 시점의 데이터를 반환한다.
  - `http_server_requests_seconds_count offset 5m`

### 범위 벡터 선택기 ([...])
- 지난 5분 동안의 데이터를 조회한다.
  - `http_server_requests_seconds_count[5m]`
- 범위 벡터 선택기는 차트에 바로 표현할 수 없다.
  - 데이터로는 확인할 수 있다.
  - increase, rate, irate 함수를 사용하면 차트로 표현할 수 있다.

### 게이지 (Gauge)
- 임의로 오르내릴 수 있는 값
- 예) 현재 메모리 사용량, 현재 스레드 개수, 현재 사용중인 Connection 개수 등

### 카운터 (Counter)
- 단순하게 증가하는 단일 누적 값
- 예) HTTP 요청 수, 로그 발생 수, 에러 발생 수 등

### increase()
- 지정한 시간 단위별로 증가를 확인할 수 있다.
- 파라미터로 범위 벡터를 넘겨줘야한다.
  - `increase(http_server_requests_seconds_count{uri="/actuator/prometheus"}[1m])`

### rate()
- 범위 벡터에서 초당 평균 증가율을 계산한다.
  - `rate(http_server_requests_seconds_count{uri="/actuator/prometheus"}[1m])`
- **초당 얼마나 증가하는지 나타내는 지표라고 보면 된다.**

### irate()
- `rate` 함수와 유사
- 범위 벡터에서 초당 순간 증가율을 계산한다.
- 급격하게 증가한 내용을 확인하는데 좋다.
  - `irate(http_server_requests_seconds_count{uri="/actuator/prometheus"}[1m])`

# Grafana
![grafana.png](docs/grafana.png)

## Grafana 공유 대시보드
[Grafana DashBoards](https://grafana.com/grafana/dashboards/)

## Grafana SpringBoot 관련 대시보드 예시
- [Spring Boot 2.1 System Monitor](https://grafana.com/grafana/dashboards/11378-justai-system-monitor/)
  - 설정 json에서 `jetty_` 를 `tomcat_` 으로 변경해야한다.
  - `jetty_threads_idle` 제거
  - `jetty_threads_jobs` 제거

- [JVM (Micrometer)](https://grafana.com/grafana/dashboards/4701)

