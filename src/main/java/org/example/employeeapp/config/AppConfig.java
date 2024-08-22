package org.example.employeeapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for application-level settings.
 */
@Configuration
public class AppConfig {

    /**
     * Bean definition for RestTemplate.
     *
     * @return A configured RestTemplate instance.
     */
    @Bean
    public RestTemplate restTemplate() {
        // Create a new instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Add a message converter to handle JSON responses
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        // Return the configured RestTemplate instance
        return restTemplate;
    }
}
