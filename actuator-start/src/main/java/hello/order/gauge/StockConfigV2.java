package hello.order.gauge;

import hello.order.OrderService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockConfigV2 {

    @Bean
    public MeterBinder stockMeterBinder(OrderService orderService) {
        return registry -> Gauge.builder("my.stock", orderService, service ->
                service.getStock().get())
            .description("stock")
            .register(registry);
    }
}
