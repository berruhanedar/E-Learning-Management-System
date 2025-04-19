package com.berru.app.elearningmanagementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Info apiInfo = new Info()
                .title("LMS API")
                .description("API documentation for the E-Learning Management System")
                .version("1.0.0")
                .license(new License()
                        .name("Apache License 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0"));

        ExternalDocumentation externalDocs = new ExternalDocumentation()
                .description("More information about the project");

        return new OpenAPI()
                .info(apiInfo)
                .externalDocs(externalDocs);
    }
}