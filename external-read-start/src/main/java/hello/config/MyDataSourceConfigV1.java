package hello.config;

import hello.datasource.MyDataSource;
import hello.datasource.MyDataSourcePropertiesV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(MyDataSourcePropertiesV1.class)
@RequiredArgsConstructor
@Slf4j
public class MyDataSourceConfigV1 {
    private final MyDataSourcePropertiesV1 myDataSourceProperties;

    @Bean
    public MyDataSource myDataSource() {
        return new MyDataSource(
            myDataSourceProperties.getUrl(),
            myDataSourceProperties.getUsername(),
            myDataSourceProperties.getPassword(),
            myDataSourceProperties.getEtc().getMaxConnection(),
            myDataSourceProperties.getEtc().getTimeout(),
            myDataSourceProperties.getEtc().getOption()
        );
    }
}
