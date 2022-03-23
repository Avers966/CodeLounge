package ru.skillbox.diplom.alpha.library.core.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * SwaggerConfig
 *
 * @author Sergey Olshevskiy
 */
@Configuration
public class SwaggerConfig {

    @Value("${swagger.path}")
    private String swaggerPath;

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(new Info()
                .title("API schema")
                .version("1.0.0")
                .description("Skillbox Alfabank Team Service API")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .servers(List.of(new Server().url(swaggerPath)));
    }
}
