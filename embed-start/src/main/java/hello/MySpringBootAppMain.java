package hello;

import hello.boot.MySpringApplication;
import hello.boot.MySpringBootApplication;
import lombok.extern.slf4j.Slf4j;

@MySpringBootApplication
@Slf4j
public class MySpringBootAppMain {

    public static void main(String[] args) {
        log.info("MySpringBootAppMain.main");

        MySpringApplication.run(MySpringBootAppMain.class, args);
    }
}
