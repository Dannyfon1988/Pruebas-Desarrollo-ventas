package com.ventas.ventasapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión de Ventas")
                        .description("CRUD completo para productos, vendedores y ventas")
                        .version("1.0.0"));
    }
}
//http://localhost:8080/swagger-ui/index.html