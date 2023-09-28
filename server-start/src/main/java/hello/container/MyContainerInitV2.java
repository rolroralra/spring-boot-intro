package hello.container;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

/**
 * @see jakarta.servlet.ServletContainerInitializer
 * @see jakarta.servlet.annotation.HandlesTypes
 */
@HandlesTypes(AppInit.class)
@Slf4j
public class MyContainerInitV2 implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        log.info("MyContainerInitV2.onStartup");
        log.info("MyContainerInitV2 c = " + c);
        log.info("MyContainerInitV2 ctx = " + ctx);

        for (Class<?> appInitClass : c) {
            try {
                AppInit appInit = (AppInit) appInitClass.getDeclaredConstructor().newInstance();

                appInit.onStartup(ctx);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
