package hello.config;

import hello.datasource.MyDataSource;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.time.Duration;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class MyDataSourceEnvironmentConfig {

    private final Environment environment;

    @Bean
    public MyDataSource myDataSource() {
        String url = environment.getProperty("MyDataSourcePropertiesV1");
        String username = environment.getProperty("MyDataSourcePropertiesV1");
        String password = environment.getProperty("MyDataSourcePropertiesV1");

        // https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config.typesafe-configuration-properties.conversion
        int maxConnection = environment.getProperty("my.datasource.etc.max-connection", Integer.class);
        var timeout = environment.getProperty("my.datasource.etc.timeout", Duration.class);
        var options = environment.getProperty("my.datasource.etc.options", List.class);

        return new MyDataSource(url, username, password, maxConnection, timeout, options);
    }
}
