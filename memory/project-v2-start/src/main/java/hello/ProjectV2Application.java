package hello;

import memory.MemoryAutoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectV2Application {

    public static void main(String[] args) {
        MemoryAutoConfig memoryAutoConfig;
        SpringApplication.run(ProjectV2Application.class, args);
    }

}
