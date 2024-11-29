package ru.shirokikh.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Client-Contact API",
                version = "1.0",
                description = "API для управления клиентами и их контактами",
                license = @License(name = "Apache 2.0", url = "http://springdoc.org")
        )
)
public class SwaggerConfig {
}
