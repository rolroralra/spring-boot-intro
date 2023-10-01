package hello.config;

import hello.datasource.MyDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

@Configuration
@Slf4j
public class MyDataSourceValueConfig {
    @Value("${MyDataSourcePropertiesV1}")
    private String url;

    @Value("${MyDataSourcePropertiesV1}")
    private String username;

    @Value("${MyDataSourcePropertiesV1}")
    private String password;

    @Value("${my.datasource.etc.max-connection:1}")
    private int maxConnection;

    @Value("${my.datasource.etc.timeout:PT10S}")
    private Duration timeout;

    @Value("${my.datasource.etc.options}")
    private List<String> options;

    @Bean
    public MyDataSource myDataSource1() {
        return new MyDataSource(url, username, password, maxConnection, timeout, options);
    }

    @Bean
    public MyDataSource myDataSource2(
        @Value("${MyDataSourcePropertiesV1}") String url,
        @Value("${MyDataSourcePropertiesV1}") String username,
        @Value("${MyDataSourcePropertiesV1}") String password,
        @Value("${my.datasource.etc.max-connection:1}") int maxConnection,
        @Value("${my.datasource.etc.timeout:PT10S}") Duration timeout,
        @Value("${my.datasource.etc.options}") List<String> options
    ) {
        return new MyDataSource(url, username, password, maxConnection, timeout, options);
    }
}
