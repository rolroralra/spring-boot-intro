package hello.external;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class EnvironmentVariableTest {
    public static void main(String[] args) {
        Map<String, String> envMap = System.getenv();

        for (String key : envMap.keySet()) {
            log.info("Environment Variable {}={}", key, envMap.get(key));
        }

        String url = System.getenv("url");
        String username = System.getenv("username");
        String password = System.getenv("password");

        log.info("url={}", url);
        log.info("username={}", username);
        log.info("password={}", password);

        assertNotNull(url);
        assertNotNull(username);
        assertNotNull(password);
    }
}
