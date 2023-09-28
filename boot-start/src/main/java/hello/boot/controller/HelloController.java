package hello.boot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {
    @GetMapping("/hello-spring")
    public String hello() {
        log.info("HelloController.hello");
        return "Hello Spring!";
    }
}
