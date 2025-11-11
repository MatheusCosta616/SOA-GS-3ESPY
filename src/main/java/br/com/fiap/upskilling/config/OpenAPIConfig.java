package br.com.fiap.upskilling.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Plataforma de Upskilling API")
                        .version("1.0.0")
                        .description("API RESTful para gerenciar usuários e trilhas de aprendizagem")
                        .contact(new Contact()
                                .name("FIAP - 3ESPY")
                                .email("contato@fiap.com.br")));
    }
}
