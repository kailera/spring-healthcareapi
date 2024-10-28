package com.example.healthcare.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Array;
import java.util.Arrays;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Api de gerenciamento de Clínicas e Sistema médico")
                        .version("1.0")
                        .description("API para gerenciar instituições, profissionais e trabalhos disponíveis na área de saúde")
                        .contact(new Contact()
                                .email("dev@example.com")
                                .name("Samuel Ferreira da Costa")))
                .servers(Arrays.asList(new Server().url("http://localhost:8080")))
                .addTagsItem(new Tag().name("Organization").description("Operações relacionadas a organizações"))
                .addTagsItem(new Tag().name("Professional").description("Operações relacionadas a profissionais"))
                .addTagsItem(new Tag().name("Work").description("Operações relacionadas a trabalhos"))
                .addSecurityItem(new SecurityRequirement());

    }
}
