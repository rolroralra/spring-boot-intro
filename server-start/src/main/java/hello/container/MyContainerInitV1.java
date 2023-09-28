package hello.container;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyContainerInitV1 implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        log.info("MyContainerInitV1.onStartup");
        log.info("MyContainerInitV1 c = " + c);
        log.info("MyContainerInitV1 ctx = " + ctx);
    }
}
