package com.likelion.rolling_paper.util.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * http://localhost:8080/swagger-ui/index.html
 */
@OpenAPIDefinition(
        info = @Info(title = "Rolling Paper", description = "롤링 페이퍼 API", version = "v1"),
        servers = {
//                @Server(url = "https://도메인", description = "서버 URL"),
                @Server(url = "http://localhost:8080", description = "로컬 URL")
        }
)
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi SwaggerOpenApi() {
        return GroupedOpenApi.builder()
                .group("Swagger-api")
                .pathsToMatch("/api/**")
                .build();
    }
}
