package hello.order.v1;

import hello.order.OrderService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class OrderServiceV1 implements OrderService {

    private final MeterRegistry meterRegistry;

    private AtomicInteger stock = new AtomicInteger(100);

    @Override
    public void order() {
        log.info("OrderServiceV1.order");
        stock.decrementAndGet();

        Counter.builder("my.order")
            .tag("class", this.getClass().getName())
            .tag("method", "order")
            .description("order")
            .register(meterRegistry).increment();
    }

    @Override
    public void cancel() {
        log.info("OrderServiceV1.cancel");
        stock.incrementAndGet();

        Counter.builder("my.order")
            .tag("class", this.getClass().getName())
            .tag("method", "cancel")
            .description("order")
            .register(meterRegistry).increment();
    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }
}
