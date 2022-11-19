package kr.co.numble.numble.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.time.LocalDateTime;

public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        final Info info = new Info()
                .version(LocalDateTime.now().toString())
                .title("씨푸드")
                .description("");

        return new OpenAPI()
                .addServersItem(new Server().url("http://43.201.133.197:8080/"))
                .addServersItem(new Server().url("http://localhost:8080/"))
                .components(new Components())
                .info(info);
    }
}