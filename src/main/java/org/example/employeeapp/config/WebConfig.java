package org.example.employeeapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Global CORS configuration for the application.
 *
 * CORS (Cross-Origin Resource Sharing) configuration allows the application's
 * resources to be accessible from different domains. This class configures CORS
 * permissions globally for the entire application.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configures CORS permissions for the application.
     *
     * It allows requests from any origin and accepts the specified HTTP methods.
     * It also permits any type of header in the requests.
     *
     * @param registry The CORS registry used to define access rules.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allows CORS configuration for all routes.
                .allowedOrigins("*") // Allows requests from any origin.
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allows the specified HTTP methods.
                .allowedHeaders("*"); // Allows any type of headers in requests.
    }
}
