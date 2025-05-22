package com.example.zippickT.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                /// [현재 Swagger UI가 열려 있는 주소]
                /// = 도메인 + 포트
                /// +
                /// .addServersItem(new Server().url("/api"))
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Zipick API") // API의 제목
                .description("Zipick 프로젝트의 API 테스트") // API에 대한 설명
                .version("1.0.0"); // API의 버전
    }
}
