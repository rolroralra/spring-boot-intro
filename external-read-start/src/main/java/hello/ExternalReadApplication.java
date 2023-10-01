package hello;

import hello.config.MyDataSourceConfigV1;
import hello.config.MyDataSourceConfigV2;
import hello.config.MyDataSourceConfigV3;
import hello.config.MyDataSourceEnvironmentConfig;
import hello.config.MyDataSourceValueConfig;
import hello.datasource.MyDataSource;
import hello.datasource.MyDataSourcePropertiesV2;
import hello.pay.PayConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Import;

//@Import(MyDataSourceEnvironmentConfig.class)
//@Import(MyDataSourceValueConfig.class)
//@Import(MyDataSourceConfigV1.class)
//@ConfigurationPropertiesScan(basePackageClasses = MyDataSourcePropertiesV2.class)
@Import(MyDataSourceConfigV3.class)
@SpringBootApplication(scanBasePackageClasses = {MyDataSource.class, PayConfig.class})
public class ExternalReadApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExternalReadApplication.class, args);
    }

}
