package hello.pay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Slf4j
public class PayConfig {
    @Bean
    @Profile({"local", "default"})
    public PayClient localPayClient() {
        log.info("localPayClient Bean Registration");
        return new LocalPayClient();
    }

    @Bean
    @Profile("prod")
    public PayClient prodPayClient() {
        log.info("prodPayClient Bean Registration");
        return new ProdPayClient();
    }
}
