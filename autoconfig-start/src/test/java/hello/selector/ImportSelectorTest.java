package hello.selector;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ImportSelectorTest {

    @Configuration
    @Import(HelloConfig.class)
    public static class StaticConfig {

    }

    @Configuration
    @Import(HelloImportSelector.class)
    public static class SelectorConfig {

    }

    @Test
    void staticConfig() {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(StaticConfig.class);

        HelloBean bean = applicationContext.getBean(HelloBean.class);

        assertNotNull(bean);
    }

    @Test
    void selectorConfig() {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(SelectorConfig.class);

        HelloBean bean = applicationContext.getBean(HelloBean.class);

        assertNotNull(bean);
    }
}
