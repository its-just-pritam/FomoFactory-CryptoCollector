package com.microservice.ff.config.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {

    @Bean
    public OpenAPI generateOpenApi() {

        Info info = new Info()
                .title("Boilerplate")
                .version("1.0")
                .description("Base Open API Configuration");

        return new OpenAPI().info(info);
    }

}
