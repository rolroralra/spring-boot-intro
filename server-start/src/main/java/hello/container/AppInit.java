package hello.container;

import jakarta.servlet.ServletContext;

/**
 * @see org.springframework.web.WebApplicationInitializer
 */
public interface AppInit {
    void onStartup(ServletContext servletContext);
}
