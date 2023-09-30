package hello;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommandLineBean {
    private final ApplicationArguments arguments;

    @PostConstruct
    public void init() {
        log.info("SourceArgs: {}", Arrays.asList(arguments.getSourceArgs()));
        log.info("OptionNames: {}", arguments.getOptionNames());

        for (String optionName : arguments.getOptionNames()) {
            log.info("Option Args: {}={}", optionName, arguments.getOptionValues(optionName));
        }
    }
}
