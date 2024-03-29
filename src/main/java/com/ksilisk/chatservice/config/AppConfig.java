package com.ksilisk.chatservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title("Chat Service API"));
    }
}
