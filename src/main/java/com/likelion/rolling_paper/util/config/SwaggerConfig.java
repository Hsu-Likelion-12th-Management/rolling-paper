package com.likelion.rolling_paper.util.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
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
                @Server(url = "https://bugi-ball.shop", description = "서버 URL"),
                @Server(url = "http://localhost:8080", description = "로컬 URL")
        },
        security = @SecurityRequirement(name = "bearerAuth") // 여기에 보안 요구 사항 추가
)
@SecurityScheme(
        name = "bearerAuth", // 보안 스키마 이름 설정
        type = SecuritySchemeType.HTTP, // HTTP 스키마 유형 설정
        scheme = "bearer", // 인증 방식 설정
        bearerFormat = "JWT" // 베어러 형식 설정 (선택 사항)
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
