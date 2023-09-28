package hello.container;

import hello.spring.HelloConfig;
import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @see org.springframework.web.WebApplicationInitializer
 * @see org.springframework.web.context.support.AnnotationConfigWebApplicationContext
 * @see org.springframework.web.servlet.DispatcherServlet
 */
@Slf4j
public class AppInitV2Spring implements AppInit {

    @Override
    public void onStartup(ServletContext servletContext) {
        log.info("AppInitV2Spring.onStartup");

        // Spring Container 생성
        AnnotationConfigWebApplicationContext applicationContext
            = new AnnotationConfigWebApplicationContext();

        applicationContext.register(HelloConfig.class);

        // Spring MVC 프레임워크의 Front Controller 인 DispatcherServlet 생성, Spring Container 연결
        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);

        // DispatcherServlet을 Servlet Container에 등록
        servletContext
            .addServlet("dispatcherV2", dispatcherServlet)
            .addMapping("/spring/*");
    }
}
