package hello.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class CommandLineV2Test {
    public static void main(String[] args) {
        for (String arg : args) {
            log.info("Command Line Argument : {}", arg);
        }

        ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);

        log.info("ApplicationArguments::getSourceArgs() = {}", Arrays.asList(applicationArguments.getSourceArgs()));
        log.info("ApplicationArguments::getNonOptionArgs() = {}", applicationArguments.getNonOptionArgs());
        log.info("ApplicationArguments::getOptionNames() = {}", applicationArguments.getOptionNames());

        for (String optionName : applicationArguments.getOptionNames()) {
            log.info("option args {}={}", optionName, applicationArguments.getOptionValues(optionName));
        }

        List<String> url = applicationArguments.getOptionValues("url");
        List<String> username = applicationArguments.getOptionValues("username");
        List<String> password = applicationArguments.getOptionValues("password");
        List<String> noExistsOptionValues = applicationArguments.getOptionValues("no-exists-option-name");

        log.info("url={}", url);
        log.info("username={}", username);
        log.info("password={}", password);
        log.info("noExistsOptionValues={}", noExistsOptionValues);


        assertThat(url).isNotEmpty();
        assertThat(username).isNotEmpty();
        assertThat(password).isEmpty();
        assertThat(noExistsOptionValues).isNull();
    }
}
