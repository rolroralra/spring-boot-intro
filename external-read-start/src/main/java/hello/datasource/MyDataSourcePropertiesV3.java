package hello.datasource;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import java.time.Duration;
import java.util.List;
import org.hibernate.validator.constraints.time.DurationMax;
import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties("my.datasource")
@Validated
public record MyDataSourcePropertiesV3(
    @NotEmpty String url,
    @NotEmpty String username,
    @NotEmpty String password,
    @DefaultValue Etc etc
) {
    public record Etc(
        @Min(1) @Max(999) @DefaultValue("1") int maxConnection,
        @DurationMin(seconds = 1) @DurationMax(seconds = 60) @DefaultValue("PT30S") Duration timeout,
        @DefaultValue("DEFAULT") List<String> options
    ) { }
}
