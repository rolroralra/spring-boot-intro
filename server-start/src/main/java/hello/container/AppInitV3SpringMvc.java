package hello.container;

import hello.spring.HelloConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @see org.springframework.web.SpringServletContainerInitializer
 * @see org.springframework.web.SpringServletContainerInitializer#onStartup
 * @see org.springframework.web.WebApplicationInitializer
 */
@Slf4j
public class AppInitV3SpringMvc implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        log.info("AppInitV3SpringMvc.onStartup");

        // Spring Container 생성
        AnnotationConfigWebApplicationContext applicationContext
            = new AnnotationConfigWebApplicationContext();

        applicationContext.register(HelloConfig.class);

        // Spring MVC 프레임워크의 Front Controller 인 DispatcherServlet 생성, Spring Container 연결
        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);

        // DispatcherServlet을 Servlet Container에 등록
        servletContext
            .addServlet("dispatcherV3", dispatcherServlet)
            .addMapping("/springboot/*");
    }
}
