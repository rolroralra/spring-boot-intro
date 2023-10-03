package hello.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TrafficController {
    private List<String> list = new ArrayList<>();

    private final DataSource dataSource;

    @GetMapping("/cpu")
    public String cpu() {
        log.info("cpu traffic request");

        long value = 0;
        for (long i = 0; i < 100_000_000_000L; i++) {
            value++;
        }

        return "ok value=" + value;
    }

    @GetMapping("/jvm")
    public String jvm() {
        log.info("jvm traffic request");

        for (int i = 0; i < 1_000_000; i++) {
            list.add("hello jvm! " + i);
        }
        return "ok";
    }

    @GetMapping("/jdbc")
    public String jdbc() throws SQLException {
        log.info("jdbc traffic request");
        Connection connection = dataSource.getConnection();
        log.info("connection={}", connection);
//        connection.close();
        return "ok";
    }

    @GetMapping("/error-log")
    public String errorLog() {
        log.error("error-log traffic request");
        return "ok";
    }
}
