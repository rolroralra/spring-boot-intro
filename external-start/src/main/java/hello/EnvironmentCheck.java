package hello;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EnvironmentCheck {

    private final Environment environment;

    @PostConstruct
    public void init() {
        String url = environment.getProperty("url");
        String username = environment.getProperty("username");
        String password = environment.getProperty("password");

        log.info("environment.getProperty(\"url\") = {}", url);
        log.info("environment.getProperty(\"username\") = {}", username);
        log.info("environment.getProperty(\"password\") = {}", password);
    }
}
