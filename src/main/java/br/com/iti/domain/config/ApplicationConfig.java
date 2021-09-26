package br.com.iti.domain.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@Log4j2
@Configuration
public class ApplicationConfig {

    @Value("${application.title}")
    private String title;

    @Value("${application.description}")
    private String description;

    @Value("${springdoc.api-docs.path}")
    private String apiDocPath;

    @Value("${springdoc.swagger-ui.path}")
    private String swaggerUiPath;

    @Value("${server.port}")
    private String port;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Bean
    public OpenAPI springShopOpenAPI(){
        log.info("Acesse SwaggerUi " + "http://localhost:"+port+contextPath+swaggerUiPath);
        return new OpenAPI()
                .info(new Info()
                        .title(convertEncondeUTF8(title))
                        .description(convertEncondeUTF8(description))
                        .version("v0.0.1")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

    private String convertEncondeUTF8(String texto){
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(texto);
        return StandardCharsets.UTF_8.decode(buffer).toString();
    }
}
