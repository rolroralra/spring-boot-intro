package hello.config;

import hello.datasource.MyDataSource;
import hello.datasource.MyDataSourcePropertiesV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(MyDataSourcePropertiesV3.class)
@RequiredArgsConstructor
@Slf4j
public class MyDataSourceConfigV3 {
    private final MyDataSourcePropertiesV3 myDataSourceProperties;

    @Bean
    public MyDataSource myDataSource() {
        return new MyDataSource(
            myDataSourceProperties.url(),
            myDataSourceProperties.username(),
            myDataSourceProperties.password(),
            myDataSourceProperties.etc().maxConnection(),
            myDataSourceProperties.etc().timeout(),
            myDataSourceProperties.etc().options()
        );
    }
}
