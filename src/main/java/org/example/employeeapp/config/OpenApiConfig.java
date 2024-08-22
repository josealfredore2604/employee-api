package org.example.employeeapp.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringDoc OpenAPI configuration class.
 *
 * Sets up SpringDoc OpenAPI for API documentation and UI.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Creates a GroupedOpenApi bean for configuring OpenAPI documentation.
     *
     * @return A GroupedOpenApi bean configured for the API.
     */
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("employees")
                .pathsToMatch("/employees/")
                .build();
    }
}
