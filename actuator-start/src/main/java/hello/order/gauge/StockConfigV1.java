package hello.order.gauge;

import hello.order.OrderService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockConfigV1 {
    @Bean
    public MyStockMetric myStockMetric(OrderService orderService, MeterRegistry meterRegistry) {
        return new MyStockMetric(orderService, meterRegistry);
    }

    @RequiredArgsConstructor
    @Slf4j
    static class MyStockMetric {

        private final OrderService orderService;

        private final MeterRegistry meterRegistry;

        @PostConstruct
        public void init() {
            Gauge.builder("my.stock", orderService, orderService -> {
                    log.info("stock gauge call");
                    return orderService.getStock().get();
                }).description("stock")
                .register(meterRegistry);
        }
    }
}
