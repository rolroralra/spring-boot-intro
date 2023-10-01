package hello.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.Duration;
import java.util.List;

@ConfigurationProperties("my.datasource")
public record MyDataSourcePropertiesV2(
    String url,
    String username,
    String password,
    @DefaultValue Etc etc
) {
    public record Etc(
        @DefaultValue("1") int maxConnection,
        @DefaultValue("PT30S") Duration timeout,
        @DefaultValue("DEFAULT") List<String> options
    ) { }
}
