package hello.order.v4;

import hello.order.OrderService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Timed("my.order")
public class OrderServiceV4 implements OrderService {

    private AtomicInteger stock = new AtomicInteger(100);

//    @Timed("my.order")
    @Override
    public void order() {
        log.info("OrderServiceV4.order");
        stock.decrementAndGet();

        sleep(500);
    }

//    @Timed("my.order")
    @Override
    public void cancel() {
        log.info("OrderServiceV4.cancel");
        stock.incrementAndGet();

        sleep(200);
    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }

    private void sleep(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds + new SecureRandom().nextInt(200));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
