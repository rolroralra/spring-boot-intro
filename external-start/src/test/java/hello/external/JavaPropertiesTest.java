package hello.external;

import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class JavaPropertiesTest {
    public static void main(String[] args) {
        // Properties extends Hashtable<Object,Object>
        Properties properties = System.getProperties();

        for (Object key : properties.keySet()) {
//            log.info("Java Property {}={}", key, properties.get(key));
//            log.info("Java Property {}={}", key, properties.getProperty(String.valueOf(key)));
            log.info("Java Property {}={}", key, System.getProperty(String.valueOf(key)));
        }

        String url = System.getProperty("url");
        String username = System.getProperty("username");
        String password = System.getProperty("password");

        log.info("url={}", url);
        log.info("username={}", username);
        log.info("password={}", password);

        assertNotNull(url);
        assertNotNull(username);
        assertNotNull(password);
    }
}
